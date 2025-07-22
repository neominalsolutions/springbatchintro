package com.mertalptekin.springbatchintro.product;


import com.mertalptekin.springbatchintro.model.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class ChunkOrientedProductJobConfig {

    @Autowired
    private ProductItemReader productItemReader;

    @Autowired
    private ProductItemWriter productItemWriter;

    @Autowired
    private ProductItemProcessor productItemProcessor;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    // JobContext'e veri set etmek ile işimiz kalmadı.Chunk Oriented Stepler zaten okuduğu veriyi processor ve write gönderecek şekilde tanımlıdır.
    @Bean
    public Step productStep() {
        return new StepBuilder("productStep",jobRepository).<Product,Product>chunk(2,platformTransactionManager).reader(productItemReader).processor(productItemProcessor).writer(productItemWriter).build();
    }

    @Bean(name = "productJob")
    public Job productJob() {
        return  new JobBuilder("productJob",jobRepository).start(productStep()).build();
    }


}
