package com.GradedProject6.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEntity {

	@Id
	private long id;
	private String username;
	private String password;
	private String roles;
}
