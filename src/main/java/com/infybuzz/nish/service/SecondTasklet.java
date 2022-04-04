package com.infybuzz.nish.service;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

@Service
public class SecondTasklet implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		//job execution context can be retrieved from inside the step
		System.out.println("now in customized !");
	    System.out.println("JEC="+chunkContext.getStepContext().getJobExecutionContext());
		System.out.println("SEC="+chunkContext.getStepContext().getStepExecutionContext());
	    return RepeatStatus.FINISHED;
	}

}
