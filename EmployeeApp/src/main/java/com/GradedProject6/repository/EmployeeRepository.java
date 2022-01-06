package com.GradedProject6.repository;

import org.springframework.stereotype.Repository;
import com.GradedProject6.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long>{

}