package com.capg.demo.step;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.capg.batch.model.Employee;
@Component
public class Processor implements ItemProcessor<Employee, Employee> {

	@Override
	public Employee process(Employee employee) throws Exception {


		final String name=employee.getName().toUpperCase();
		final Employee transformedEmployee=new Employee(employee.getId(),name, employee.getPrice(), new Date());
		
		return transformedEmployee;
	}

}
