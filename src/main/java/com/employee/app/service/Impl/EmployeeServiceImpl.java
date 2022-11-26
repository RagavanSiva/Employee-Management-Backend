package com.employee.app.service.Impl;

import com.employee.app.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.app.model.Employee;
import com.employee.app.repository.EmployeeRepository;
import com.employee.app.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee =  employeeRepository.findById(id);
//		if(employee.isPresent()){
//			return  employee.get();
//		}else{
//			throw  new ResourceNotFoundException("Employee","id",id);
//		}

		return  employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {

		//need to check whether employee with given id is existed
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->
				new ResourceNotFoundException("Employee","id",id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());

		//saving existing employee
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public Employee updateEmployeeByRequestBody(Employee employee) {

		Employee existingEmployee = employeeRepository.findById(employee.getId()).orElseThrow(()->
				new ResourceNotFoundException("Employee","id",employee.getId()));

		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());

		//save
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	//delete Employee
	@Override
	public void deleteEmployee(long id) {
		employeeRepository.findById(id).orElseThrow(()->
				new ResourceNotFoundException("Employee","id",id));
		employeeRepository.deleteById(id);
	}



}
