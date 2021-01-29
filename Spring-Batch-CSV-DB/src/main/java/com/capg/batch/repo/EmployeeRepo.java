package com.capg.batch.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.batch.model.Employee;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
