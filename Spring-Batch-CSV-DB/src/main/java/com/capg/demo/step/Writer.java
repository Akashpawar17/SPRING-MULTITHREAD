package com.capg.demo.step;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capg.batch.model.Employee;
import com.capg.batch.repo.EmployeeRepo;
@Component
public class Writer implements ItemWriter<Employee> {
	
	@Autowired
	private  EmployeeRepo employeeRepo;

	@Override
	public void write(List<? extends Employee> emp) throws Exception {
		System.out.println("Data Saved for Users:"+emp);
		employeeRepo.saveAll(emp);
	}

}
