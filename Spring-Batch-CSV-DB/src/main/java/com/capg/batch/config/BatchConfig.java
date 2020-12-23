package com.capg.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.capg.batch.model.Employee;
import com.capg.demo.step.Processor;
import com.capg.demo.step.Writer;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	DataSource dataSource;
	
	
	
	@Bean
	public Job processJob(ItemReader<Employee> itemReader,ItemProcessor<Employee, Employee> itemProcessor,ItemWriter<Employee> itemWriter) {
		
		
		Step step=stepBuilderFactory.get("ETl-filr-load")
				.<Employee,Employee>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build()
				
				;
		
		
		return	jobBuilderFactory.get("ETL-Load")
				.incrementer(new RunIdIncrementer())
				.start(step)
				.build();
		
	
		
		
	}
	@Bean
	public FlatFileItemReader<Employee> itemReader()
	{
		//INORDER TO USE THAT PARTICULAR FILE
		FlatFileItemReader<Employee> flatFileItemReader=new FlatFileItemReader<>();
		//add the resource
		flatFileItemReader.setResource(new FileSystemResource("/Spring-Batch-CSV-DB/src/main/resources/employee.csv"));
		//set the default values
		flatFileItemReader.setName("CSV-Reader");
		//set the no.of line if ther is any issue
		flatFileItemReader.setLinesToSkip(1);
		//finally we need to add(map) that to the Employee class
		flatFileItemReader.setLineMapper(LineMapper());
		
		return flatFileItemReader;
		
	}

	@Bean
	public LineMapper<Employee> LineMapper() {
		//we need to create defaultlinemapper
		DefaultLineMapper<Employee> defaultLineMapper =new DefaultLineMapper<>();
		//it is a comma seperated file so we need to add a tokenizerlinemapeer
		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
	lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id", "name","salary");

		//we need to set each field with particular pojo(Employee class)
		BeanWrapperFieldSetMapper<Employee> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Employee.class);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		 defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		
		
		return defaultLineMapper;
	}
	
	
	
	
	
}
