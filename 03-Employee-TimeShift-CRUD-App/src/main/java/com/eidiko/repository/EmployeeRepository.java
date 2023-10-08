package com.eidiko.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eidiko.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	public Optional<Employee> findByEmpName(String username);

	@Query("select e from Employee e where e.isDeleted = false")
	public List<Employee> getActiveEmployee();

	@Query("select e from Employee e where e.isDeleted = true")
	public List<Employee> getInactiveEmployeeOnly();
}
