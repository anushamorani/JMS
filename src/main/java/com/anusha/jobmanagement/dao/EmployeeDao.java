package com.anusha.jobmanagement.dao;

import com.anusha.jobmanagement.model.Employee;
import org.springframework.data.repository.CrudRepository;


public interface EmployeeDao extends CrudRepository<Employee, Long> {

}
