package com.java.batch;

import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.java.model.Employee;

@Configuration
@EnableBatchProcessing
public class BatchJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilder;

	@Autowired
	private StepBuilderFactory stepBuilder;

	@Value("classPath:/csv/employees.csv")
	private Resource csvResource;

	@Autowired
	private DataSource dataSource;

	/*
	 * @Bean("job") public Job readCSVFile() { return
	 * jobBuilder.get("readCSVFile").incrementer(new
	 * RunIdIncrementer()).start(step()).build(); }
	 */
	
	/*
	 * public TaskExecutor taskExecutor() { SimpleAsyncTaskExecutor taskExecutor =
	 * new SimpleAsyncTaskExecutor(); taskExecutor.setConcurrencyLimit(5); return
	 * taskExecutor; }
	 */

	
	public ItemProcessor<Employee, Employee> processor() {
		return new EmployeeProcessor();
	}

	// reading from csv file


	public FlatFileItemReader<Employee> reader() {
		FlatFileItemReader<Employee> itemReader = new FlatFileItemReader<>();
		itemReader.setLineMapper(lineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(csvResource);
		return itemReader;
	}

	// convert csv rows to beans


	public LineMapper<Employee> lineMapper() {
		DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "firstName", "lastName", "department" });
		lineTokenizer.setIncludedFields(new int[] { 0, 1, 2 });
		BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<Employee>();
		fieldSetMapper.setTargetType(Employee.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

	// reading from csv file

	
	public FlatFileItemReader<Employee> reader1() {
		FlatFileItemReader<Employee> itemReader = new FlatFileItemReader<>();
		itemReader.setLineMapper(lineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(csvResource);
		return itemReader;
	}

	// convert csv rows to beans

	
	public LineMapper<Employee> lineMapper1() {
		DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "firstName", "lastName", "department" });
		lineTokenizer.setIncludedFields(new int[] { 0, 1, 2 });
		BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<Employee>();
		fieldSetMapper.setTargetType(Employee.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

	// writting into mysql database

	
	@Bean
	public Job parallelStepsJob() {
		Flow secondFlow = new FlowBuilder<Flow>("secondFlow").start(step2()).build();

		Flow parallelFlow = new FlowBuilder<Flow>("parallelFlow")
				.start(step1())
				.split(new SimpleAsyncTaskExecutor())
				.add(secondFlow).build();

		return jobBuilder.get("parallelStepsJob").start(parallelFlow).end().build();
	}

	/*
	 * @Bean public Job sequentialStepsJob() { return
	 * this.jobBuilder.get("sequentialStepsJob").start(step1()).next(step2()).build(
	 * ); }
	 */
	@Bean(name="step1")
	public Step step1() {

		return stepBuilder.get("step").<Employee, Employee>chunk(5).reader(reader()).processor(processor())
				.writer(writer()).build();
	}

	@Bean(name = "step2")
	public Step step2() {

		return stepBuilder.get("step").<Employee, Employee>chunk(5).reader(reader1()).processor(processor())
				.writer( writer()).build();
	}
	
	
	
	
	@Bean
	public JdbcBatchItemWriter<Employee> writer() {
		JdbcBatchItemWriter<Employee> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql(
				"INSERT INTO EMPLOYEE (FIRSTNAME, LASTNAME, DEPARTMENT) VALUES (:firstName, :lastName, :department)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Employee>());
		return itemWriter;
	}

}