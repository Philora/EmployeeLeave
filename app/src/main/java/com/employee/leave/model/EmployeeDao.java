package com.employee.leave.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public  interface EmployeeDao {

    @Query("SELECT * from employee_table")
    LiveData<List<Employee>> getEmpList();
/*
    @Insert
    public void addEmpLeave(EmployeeLeave employeeLeaf);

    @Query("SELECT * from emp_leave_table")
    LiveData<List<EmployeeLeave>> getEmpLeaveList();*/
}
