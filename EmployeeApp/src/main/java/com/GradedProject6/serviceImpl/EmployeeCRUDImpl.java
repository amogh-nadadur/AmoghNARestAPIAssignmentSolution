package com.GradedProject6.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.GradedProject6.entity.EmployeeEntity;
import com.GradedProject6.repository.EmployeeRepository;

@Service
public class EmployeeCRUDImpl {
	@Autowired
	EmployeeRepository obj;


	
	public List<EmployeeEntity> getAll() {
		return obj.findAll();
	}
	
	public EmployeeEntity getOne(long id) {
		Optional<EmployeeEntity> result = obj.findById(id);

		EmployeeEntity theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + id);
		}

		return theEmployee;
	}
	
	
	public String save(EmployeeEntity theEmployee) {
		obj.saveAndFlush(theEmployee);
		return "Employee saved";
	}
	
	
	public String deleteId(Long id) {
		obj.deleteById(id);
		return "Deleted employee id" + id;
	}
	
	public List<EmployeeEntity> searchBy(String firstName) {
		EmployeeEntity employee = new EmployeeEntity();
		employee.setFirstName(firstName);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("firstName", 
				ExampleMatcher.GenericPropertyMatchers.exact()).withIgnorePaths("id","lastName","emailId");
		Example<EmployeeEntity> example = Example.of(employee, exampleMatcher);
		return obj.findAll(example);
	}

	public List<EmployeeEntity> getEmployeesCustomSortedByName(Direction direction) {
		return obj.findAll(Sort.by(direction, "firstName"));
	}
	
}