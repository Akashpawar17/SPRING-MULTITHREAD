package com.java.batch;
 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.java.model.Employee;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee>{
	

	  private static final Logger LOGGER =
		      LoggerFactory.getLogger(EmployeeProcessor.class);
    public Employee process(Employee employee) throws Exception
    {
    	//long t=new Thread().currentThread().getId();
    	Thread t=new Thread();
    	LOGGER.info("Inserting employee '{}'", employee+"Thread Id:"+t.getId());
        return employee;
    }
}