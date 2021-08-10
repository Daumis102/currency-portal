package com.seb.currencyportal.services;

import com.seb.currencyportal.models.SchedulerJobInfo;
import com.seb.currencyportal.repositories.SchedulerRepository;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.quartz.CronScheduleBuilder; 

@Service
public class SchedulerJobService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private SchedulerRepository schedulerRepository;

    @Autowired
    private LBService lbService;

    public void scheduleNewJob(SchedulerJobInfo jobInfo) {
        try {
            scheduler = schedulerFactoryBean.getScheduler();
            JobDetail jobDetail = JobBuilder
                    .newJob((Class<? extends Job>) Class.forName(jobInfo.getJobClass()))
                    .withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();

                // jobDetail.getJobDataMap().put("LBService", this.lbService);

            if (!scheduler.checkExists(jobDetail.getKey())) {
    
                Trigger trigger = createCronTrigger(jobInfo.getJobName(), jobInfo.getCronExpression());  
                scheduler.scheduleJob(jobDetail, trigger);
                jobInfo.setJobStatus("SCHEDULED");
                schedulerRepository.save(jobInfo);
                System.out.println(String.format("Job %s scheduled successfuly", jobInfo.getJobName()));
            } else {
                System.out.println("scheduleNewJobRequest.jobAlreadyExist");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static Trigger createCronTrigger(String triggerName, String cron) { 

        Trigger triggerNew = TriggerBuilder.newTrigger().withIdentity(triggerName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron)).build(); 

        return triggerNew; 
    }

}