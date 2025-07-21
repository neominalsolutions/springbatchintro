package com.mertalptekin.springbatchintro;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

// Normal bir servis gibi Spring Bunu Cron bazlı çalıştırıcak
@Component
@EnableScheduling
public class JobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("job2")
    private Job job;

    @Scheduled(cron ="0 * * * * *")
    //@Scheduled(initialDelay = 5000, fixedDelay = 5000)
    //@Scheduled(fixedRate = 5000) // fixedRate, fixedDelay job bitiminden 5000 ms sonra
    public  void runJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        try {
            JobParameters jobParameters = new JobParametersBuilder().addDate("jobDate", new Date()).toJobParameters();
            jobLauncher.run(job,jobParameters);
            System.out.println("Cron Job completed");
        }
         catch (Exception e) {
         }

    }
}
