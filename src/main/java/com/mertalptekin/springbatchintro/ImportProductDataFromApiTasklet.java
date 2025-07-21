package com.mertalptekin.springbatchintro;


import com.mertalptekin.springbatchintro.model.Product;
import com.mertalptekin.springbatchintro.model.ProductResponseDto;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.JobContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ImportProductDataFromApiTasklet implements Tasklet {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        try {

            // çekilen verileri reader,writer ve process gibi yapılar. taskletde olmadığı verinin farklı bir stepde işlenme durumunda bizim bu verileri executionContext göndermemiz gerekir.
            // Uyarı Not: Tasklet kullanıyorsa, her tasklet için bir step mekanizması olduğundan StepExuctionContext üzerinde farklı stepe değer taşıyamayız. Bu sebeple tüm steplerileri kapsayan JobExecutionContext'e ilgili değerimi set ediceğiz.

            String apiUrl = "https://services.odata.org/northwind/northwind.svc/Products?$format=json";

            ResponseEntity<ProductResponseDto> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    ProductResponseDto.class);

            List<Product> data = response.getBody().getValue();

            var jobExecutionContext = chunkContext.getStepContext().getJobExecutionContext();
            // bu tarz veri saklama işlemleri stepler arasında runtimeda veri taşıma amaçlı kullanılır.
            jobExecutionContext.put("products", data);

            // Koşullu bir duruma göre Step'i Failed etmek istersek ?
            // StepExution Tablosunda Stateleri koşullu olarak set etmek için manuel olarka yönetim yapabilir.
            if (data.size() < 1) {
                StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
                stepExecution.setStatus(BatchStatus.FAILED);
                stepExecution.setExitStatus(ExitStatus.UNKNOWN);
            }


        } catch (RuntimeException e) {
            // Eğer bir tasklet içerisinde bir exception oluştursa Step otomatik olarak Failed olarak işaretlenir. Tabi durumda step hatalı olduğu için Jobda Failed olur.
            // Not: Exception varsa otomatik FAILED olur.
            System.out.println(e.getMessage());
        }

        return RepeatStatus.FINISHED;
    }
}
