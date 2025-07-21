package com.mertalptekin.springbatchintro;

import com.mertalptekin.springbatchintro.model.Product;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrintCriticalStockTasklet implements Tasklet {


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

       List<Product> filteredProducts = (List<Product>)chunkContext.getStepContext().getJobExecutionContext().get("filteredProducts");

       contribution.incrementReadCount(); // 1 adet veri okuma işlemi yapıldı
       contribution.incrementWriteCount(filteredProducts.size()); // Toplamda Bu Step içerisinde bu kadar veri üzerinde yazma işlemi yapıldı
        contribution.incrementFilterCount(filteredProducts.size());

        System.out.println("PrintCriticalStockTasklet");

        filteredProducts.forEach(x->
               System.out.println(x.getName() + " " + x.getStock().toString()));


        return RepeatStatus.FINISHED;
    }
}
