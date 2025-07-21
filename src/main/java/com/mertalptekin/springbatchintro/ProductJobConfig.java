package com.mertalptekin.springbatchintro;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

// Bu dosyada ProductJob tanımlama işlemleri yaparız.


@Configuration
@EnableBatchProcessing
public class ProductJobConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

//    @Autowired
//    private Tasklet1 tasklet1;
    // Tasklet ile basit bir step adımı oluşturularım

    @Autowired
    private ImportProductDataFromApiTasklet importProductDataFromApiTasklet;

    @Autowired
    private PrintCriticalStockTasklet printCriticalStockTasklet;

    @Autowired
    private  FilterCriticalStockTasklet filterCriticalStockTasklet;

//    @Bean
//    public Step step1() {
//        return  new StepBuilder("step1",jobRepository)
//                .tasklet(tasklet1,transactionManager).build();
//    }
//
//    @Bean(name = "job1")
//    public Job job1() {
//        return new JobBuilder("job1",jobRepository).start(step1()).build();
//    }

    // Yeni Implementasyon

    @Bean
    public  Step productFromApiStep(){
        return  new StepBuilder("productFromApiStep",jobRepository).tasklet(importProductDataFromApiTasklet,transactionManager).build();
    }

    @Bean
    public  Step filterCriticalStockStep(){
        return  new StepBuilder("filterCriticalStockStep",jobRepository).tasklet(filterCriticalStockTasklet,transactionManager).build();
    }

    @Bean
    public Step printCriticalStockStep(){
        return  new StepBuilder("printCriticalStockStep",jobRepository).tasklet(printCriticalStockTasklet,transactionManager).build();
    }


    @Bean(name = "job2")
    public Job job2() {
        return new JobBuilder("job2",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(productFromApiStep()).next(filterCriticalStockStep()).next(printCriticalStockStep()).build();
    }


}
