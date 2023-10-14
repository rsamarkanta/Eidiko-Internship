package com.eidiko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eidiko.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT DISTINCT e " + "FROM Employee e " + "JOIN e.skillList s " + "JOIN e.performanceList r "
			+ "WHERE e.flag = false " + // Assuming flag is a boolean (true/false) field
			"AND s.working = 'trainee' " + "AND ((r.techRating < 2.5 AND r.communicationRating < 2.5) "
			+ "      OR r.techRating < 2.5 OR r.communicationRating < 2.5) ")
	List<Employee> findEmployeesWithFilteredConditions();

}
