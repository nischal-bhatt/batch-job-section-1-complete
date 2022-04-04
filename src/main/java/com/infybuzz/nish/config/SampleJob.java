package com.infybuzz.nish.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.infybuzz.nish.listener.FirstJobListener;
import com.infybuzz.nish.listener.SecondStepListener;
import com.infybuzz.nish.service.SecondTasklet;

@Configuration
public class SampleJob {

	@Autowired
	private FirstJobListener firstJobListener;
	
	@Autowired
	private SecondStepListener secondStepListener;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SecondTasklet secondTasklet;

	@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("First-Job").incrementer(new RunIdIncrementer()).start(firstStep()).next(secondStep()).next(thirdStep()).listener(firstJobListener).build();
	}

	private Step firstStep() {
		return this.stepBuilderFactory.get("first-Step").tasklet(firstTask()).build();
	}

	private Step secondStep() {
		return this.stepBuilderFactory.get("second-Step").tasklet(secondTasklet).listener(secondStepListener).build();
	}

	private Step thirdStep() {
		return this.stepBuilderFactory.get("third-Step").tasklet(thirdTask()).build();
	}

	private Tasklet thirdTask() {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("this is third jobber");
				return RepeatStatus.FINISHED;
			}

		};
	}

	private Tasklet firstTask() {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("this is first jobber");
				return RepeatStatus.FINISHED;
			}

		};
	}
	
	
	
	/*
	 * private Tasklet secondTask() { return new Tasklet() {
	 * 
	 * @Override public RepeatStatus execute(StepContribution contribution,
	 * ChunkContext chunkContext) throws Exception {
	 * System.out.println("this is second Tasklet jobber"); return
	 * RepeatStatus.FINISHED; }
	 * 
	 * }; }
	 */
}
