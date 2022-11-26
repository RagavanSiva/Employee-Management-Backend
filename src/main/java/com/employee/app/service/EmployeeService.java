package com.employee.app.service;

import com.employee.app.model.Employee;

import java.util.List;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(long id);

	Employee updateEmployee(Employee employee, long id);

	Employee updateEmployeeByRequestBody(Employee employee);

	void deleteEmployee(long id);
}
