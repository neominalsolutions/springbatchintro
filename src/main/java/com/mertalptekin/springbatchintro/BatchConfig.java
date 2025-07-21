package com.mertalptekin.springbatchintro;

// Job ve Repository bağlantı kodları burada olsun
// EnableBatchProcessing anatasyonu ile JobRepo ve Transation Bean tanımları

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    // APIDEN veri çekmek için kullanılacak olan Bean
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    // JobRepository registeration

    @Bean
    public JobRepository jobRepository(DataSource dataSource, DataSourceTransactionManager transactionManager) throws Exception{
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("H2");
        jobRepositoryFactoryBean.afterPropertiesSet();
        return  jobRepositoryFactoryBean.getObject();
    }

    // JobRepository Transaction yönetimi
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }



}
