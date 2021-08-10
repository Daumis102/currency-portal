package com.seb.currencyportal.components;

import javax.annotation.PostConstruct;

import com.seb.currencyportal.jobs.GetNewestRatesJob;
import com.seb.currencyportal.models.SchedulerJobInfo;
import com.seb.currencyportal.services.SchedulerJobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Init {
    @Autowired
    SchedulerJobService schedulerService;
    
    @PostConstruct
    public void init() {
        // Schedule the currency rates update from LB every midnight
        SchedulerJobInfo job = new SchedulerJobInfo("UpdateRates", "Rates",GetNewestRatesJob.class.getName(), "0 0 0 * * ?");
		schedulerService.scheduleNewJob(job);
    }
}
