package com.mertalptekin.springbatchintro;

import com.mertalptekin.springbatchintro.model.Product;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterCriticalStockTasklet implements Tasklet {


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        try{

          List<Product> products =  (List<Product>)chunkContext.getStepContext().getJobExecutionContext().get("products");

          List<Product> filteredProducts = products.stream().filter(x-> x.getStock() < 15).toList();

          chunkContext.getStepContext().getJobExecutionContext().put("filteredProducts", filteredProducts);

            System.out.println("FilterCriticalStockTasklet");

          return RepeatStatus.FINISHED;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
