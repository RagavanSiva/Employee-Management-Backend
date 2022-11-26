package com.employee.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employee.app.model.Employee;
import com.employee.app.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	
	
	//create Employee RestAPI
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.CREATED );
	}


	//get all Employees
	@GetMapping
	public List<Employee> getAllEmployess(){
		return employeeService.getAllEmployees();
	}

	//gellEmployeeByID
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK) ;
	}

	//updateEmployee
	@PutMapping("{id}")
	public  ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable("id") long employeeId){
		return  new ResponseEntity<Employee>(employeeService.updateEmployee(employee,employeeId),HttpStatus.OK);
	}

	//update Employee by requestbody
	@PutMapping
	public ResponseEntity<Employee> updateEmployeeByRequestBody(@RequestBody Employee employee){
		return  new ResponseEntity<Employee>(employeeService.updateEmployeeByRequestBody(employee),HttpStatus.OK);
	}

	//delete Employee
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee deleted successfully",HttpStatus.OK);
	}
}
