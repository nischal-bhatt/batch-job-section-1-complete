package com.infybuzz.nish.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("before job");
		System.out.println(jobExecution.getJobInstance().getJobName());
		System.out.println(jobExecution.getJobParameters());
		System.out.println(jobExecution.getExecutionContext());
	    jobExecution.getExecutionContext().put("jec", "jec value");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("after job");
		System.out.println(jobExecution.getJobInstance().getJobName());
		System.out.println(jobExecution.getJobParameters());
		System.out.println(jobExecution.getExecutionContext());
	}
   
}
