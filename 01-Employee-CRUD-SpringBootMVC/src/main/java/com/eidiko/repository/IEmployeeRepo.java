package com.eidiko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eidiko.model.Employee;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Integer> {

}
