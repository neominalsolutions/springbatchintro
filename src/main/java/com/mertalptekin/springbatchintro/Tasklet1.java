package com.mertalptekin.springbatchintro;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

// Not: Taskletler yapıları basit veri işlemleri için kullanıldığında dolayı hata yönetimleri manuel olarak yapılır. contribution bu ilgili okuma yazma, atlatma durumlarının okunması ve yönetimi için kullanılır.

@Component
public class Tasklet1 implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        System.out.println("Tasklet1 executed");
        return RepeatStatus.FINISHED;
    }
}
