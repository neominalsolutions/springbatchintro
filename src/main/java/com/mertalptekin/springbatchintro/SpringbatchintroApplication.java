package com.mertalptekin.springbatchintro;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

@SpringBootApplication
public class SpringbatchintroApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbatchintroApplication.class, args);

//        try {
//            ApplicationContext context = SpringApplication.run(SpringbatchintroApplication.class, args);
//
//            JobLauncher launcher = context.getBean(JobLauncher.class);
//            Job job = context.getBean("job1",Job.class);
//
////            JobParameters jobParameters = new JobParametersBuilder()
////                    .addString("jobId", "UUID.randomUUID().toString()").toJobParameters();
//
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addString("jobName", "job1").toJobParameters();
//
//            JobExecution je = launcher.run(job,jobParameters);
//
//
//
//            System.out.println("BatchStatus" + je.getStatus()); // COMPLETED, FAILED
//            System.out.println("ExitStatus"+ je.getExitStatus()); // NOOP
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e);
//        }

    }

}
