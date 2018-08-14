package com.employee.leave.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "emp_leave_table")
public class EmployeeLeaveEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "emp_leave_name")
    public String empMName;

    @ColumnInfo(name = "emp_leave_from")
    public String empMLeaveFrom;

    @ColumnInfo(name = "emp_leave_to")
    public String empMLeaveTo;

    public EmployeeLeaveEntity(String empMName, String empMLeaveFrom, String empMLeaveTo) {
        this.empMName = empMName;
        this.empMLeaveFrom = empMLeaveFrom;
        this.empMLeaveTo = empMLeaveTo;
    }

    public String getEmpMName() {
        return empMName;
    }

    public EmployeeLeaveEntity setEmpMName(String empMName) {
        this.empMName = empMName;
        return this;
    }

    public String getEmpMLeaveFrom() {
        return empMLeaveFrom;
    }

    public EmployeeLeaveEntity setEmpMLeaveFrom(String empMLeaveFrom) {
        this.empMLeaveFrom = empMLeaveFrom;
        return this;
    }

    public String getEmpMLeaveTo() {
        return empMLeaveTo;
    }

    public EmployeeLeaveEntity setEmpMLeaveTo(String empMLeaveTo) {
        this.empMLeaveTo = empMLeaveTo;
        return this;
    }
}
