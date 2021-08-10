package com.seb.currencyportal.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.seb.currencyportal.models.Rate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Service
public class LBService {

    @Autowired
    RateDbService rateDbService;

    public void updateDatabase() throws IOException{
        ArrayList<Rate> rates = getCurrencyRates();
        for (Rate rate : rates) {
            rateDbService.saveOrUpdate(rate);
        }

    }

    public ArrayList<Rate> getCurrencyRates() throws IOException{
        
        String currencyRatesXml = requestCurrencyRates();
        try {
            return xml2HashMap(currencyRatesXml);
        } catch (SAXException | ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ArrayList<Rate>();
        }
    }

    private String requestCurrencyRates() throws IOException{
        URL url = new URL("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try {
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestMethod("GET");
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        }

        BufferedReader br = new BufferedReader(
        new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
    }

    private ArrayList<Rate> xml2HashMap(String ratesXml) throws IOException, SAXException, ParserConfigurationException{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new ByteArrayInputStream(ratesXml.getBytes()));

        ArrayList<Rate> array = new ArrayList<>();

        NodeList fxRateNodes = doc.getElementsByTagName("FxRate");
        System.out.println(fxRateNodes.toString());

        for(int k = 0; k < fxRateNodes.getLength(); k++){
            Node fxRateNode = fxRateNodes.item(k);
            NodeList fxRateData = fxRateNode.getChildNodes();

            NodeList data = fxRateData.item(3).getChildNodes();

            String date = fxRateData.item(1).getTextContent();
            String currency = data.item(0).getTextContent();
            Float rate = Float.parseFloat(data.item(1).getTextContent());

            Rate rateData = new Rate(currency, date, rate);
            array.add(rateData);

        }
        return array;

    }
}
