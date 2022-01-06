package com.GradedProject6.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;
import com.GradedProject6.entity.EmployeeEntity;
import com.GradedProject6.entity.UserEntity;
import com.GradedProject6.serviceImpl.EmployeeCRUDImpl;
import com.GradedProject6.serviceImpl.UserImpl;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeCRUDImpl obj;
	
	@Autowired
	UserImpl userObj;
	
	@GetMapping("/")
	public String homePage() {
		return "WELCOME TO THE HOMEPAGE";
	}
	
	//1.Your application should be able to add roles in the database dynamically in the db.
	//2. Your application should be able to add Users in the db which can be used for authentication purposes.
	
	@GetMapping("/add/{id}/{name}/{password}")
	public UserEntity addUser(@PathVariable long id, @PathVariable String name, @PathVariable String password) {
		UserEntity user = UserEntity.builder().id(id).username(name).password(password).roles("USER").build();
		userObj.addUser(user);
		return user;
	}
	
	@GetMapping("/listUsers")
	public List<UserEntity> listUsers() {
		return userObj.listUsers();
	}
	
	@GetMapping("/addEmployee")
	public String addEmp() {
		EmployeeEntity employee = EmployeeEntity.builder().id(4).firstName("Amogh").lastName("N.A").emailId("sadadaa@gmail.com").build();
		return obj.save(employee);
	}
	
	
	//3. Now Your application should be able to add employees data in the db if and only if the authenticated user is ADMIN-
	
	
	//4. Your application should provide an endpoint to list all the employees stored in the database.
	@GetMapping("/list")
	public List<EmployeeEntity> listAll() {
		return obj.getAll();
	}

	//5. Your application should provide endpoint to fetch or get an employee record specifically based on the id of that employee
	@RequestMapping(path="get/{Id}")
	public EmployeeEntity listOne(@PathVariable("Id") Long Id){
		return obj.getOne(Id);
	}

	//6. Your application should provide an endpoint to update an existing employee record with the given updated json object.
	@PutMapping("/updateEmployees/{id}")
	public EmployeeEntity updateEmployee(@RequestBody EmployeeEntity theEmployee, @PathVariable long id) {
		EmployeeEntity tempEmployee = obj.getOne(id);

		if (tempEmployee == null) {
			throw new RuntimeException("Employee with id " + id + " not found");
		}
		tempEmployee.setFirstName(theEmployee.getFirstName());
		tempEmployee.setLastName(theEmployee.getLastName());
		tempEmployee.setEmailId(theEmployee.getEmailId());
		obj.save(theEmployee);
		theEmployee.setId(id);
		return theEmployee;
	}
	
	//7. Your application should also provide an endpoint to delete an existing employee record based on the id of the employee
	@RequestMapping(path="delete/{Id}")
	public String deleteEmployee(@PathVariable("Id") Long Id) {
			return obj.deleteId(Id);
	}

	
	//8.  Your application should provide an endpoint to fetch an employee by his/her first name and if found more than one record then list them all-
	@GetMapping("/search")
	public List<EmployeeEntity> searchBy(@RequestParam("firstName") String firstName) {
		return obj.searchBy(firstName);
	}

	
	//9. Your application should be able to list all employee records sorted on their first name in either ascending order or descending order .
	@GetMapping("/sort/{direction}")
	public List<EmployeeEntity> getEmployeesCustomSortedByName(@PathVariable Direction direction) {
		return obj.getEmployeesCustomSortedByName(direction);
	}
}