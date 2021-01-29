package com.example.demo.config;
 

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.model.Employee;
 
public class EmployeeProcessor implements ItemProcessor<Employee, Employee>{
	
	 
    public Employee process(Employee employee) throws Exception
    {
    Employee e=new Employee();
    e.setId(employee.getId());
    e.setFirstName(employee.getFirstName());
    e.setLastName(employee.getLastName());
    e.setDepartment(employee.getDepartment());
    System.out.println("data" +e);
        return e;
    }
}