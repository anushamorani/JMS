package com.anusha.jobmanagement.dao;

import com.anusha.jobmanagement.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeDao extends CrudRepository<Employee, Long> {

}
