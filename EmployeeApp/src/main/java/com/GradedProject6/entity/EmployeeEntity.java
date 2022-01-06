package com.GradedProject6.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmployeeEntity {

	@Id
	private long id;
	private String firstName;
	private String lastName;
	private String emailId;
}
