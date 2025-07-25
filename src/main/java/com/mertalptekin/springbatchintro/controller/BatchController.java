package com.mertalptekin.springbatchintro.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/batch")
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;


    @Autowired
    @Qualifier("job2")
    private Job job;

    @PostMapping("startJob2")
    public ResponseEntity<String> startJob2() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        JobParameters jobParameters = new JobParametersBuilder().addString("jobId", UUID.randomUUID().toString()).toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job,jobParameters);

        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("Job completed");
        }

        return ResponseEntity.ok("Job started");
    }


}
