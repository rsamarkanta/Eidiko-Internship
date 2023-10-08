package com.eidiko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eidiko.entity.ShiftTime;

@Repository
public interface ShiftTimeRepo extends JpaRepository<ShiftTime, Long> {
	List<ShiftTime> findByEmployeeEmpId(Long empId);

	@Query("SELECT s FROM ShiftTime s WHERE s.employee.empId = :empId ORDER BY s.startDate DESC")
	ShiftTime findLatestShiftTimeByEmpId(@Param("empId") Long empId);
}
