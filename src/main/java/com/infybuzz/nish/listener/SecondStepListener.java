package com.infybuzz.nish.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class SecondStepListener implements StepExecutionListener{

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("before");
		System.out.println(stepExecution.getStepName());
		System.out.println(stepExecution.getJobExecution().getExecutionContext());
		System.out.println(stepExecution.getExecutionContext());
		stepExecution.getExecutionContext().put("sec", "sec value");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("after");
		System.out.println(stepExecution.getStepName());
		System.out.println(stepExecution.getJobExecution().getExecutionContext());
		System.out.println(stepExecution.getExecutionContext());
		
		return null;
	}

	
}
