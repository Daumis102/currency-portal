package com.seb.currencyportal.jobs;


import java.io.IOException;

import com.seb.currencyportal.services.LBService;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class GetNewestRatesJob implements Job {

    @Autowired
    LBService lbService;
    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
        // JobDataMap data = jExeCtx.getJobDetail().getJobDataMap();
        // LBService lbService = (LBService)data.get("LBService");

        try {
            lbService.updateDatabase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }  
}