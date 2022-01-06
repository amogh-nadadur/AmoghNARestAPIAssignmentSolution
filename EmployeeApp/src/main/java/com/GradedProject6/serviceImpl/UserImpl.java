package com.GradedProject6.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GradedProject6.entity.UserEntity;
import com.GradedProject6.repository.UserRepository;

@Service
public class UserImpl {
	@Autowired 
	UserRepository obj;
	
	public void addUser(UserEntity user) {
		obj.saveAndFlush(user);
	}

	public List<UserEntity> listUsers() {
		return obj.findAll();
	}
	
	
}
