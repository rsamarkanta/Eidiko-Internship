package com.eidiko.service;

import java.util.List;

import com.eidiko.entity.ShiftTime;

public interface ShiftTimeService {

	public String saveShiftByEmployeeId(ShiftTime shift, Long empId);

	public ShiftTime getShiftByShiftId(Long shiftId);

	public List<ShiftTime> getAllShift();

	public List<ShiftTime> getAllShiftsByEmployeeId(Long empId);

}
