package com.employee.leave.util;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.employee.leave.model.Employee;
import com.employee.leave.model.EmployeeDao;
import com.employee.leave.model.EmployeeLeaveDao;
import com.employee.leave.model.EmployeeLeaveEntity;


@Database(entities = {Employee.class, EmployeeLeaveEntity.class}, version = 1, exportSchema = false)
public abstract class EmployeeDatabase extends RoomDatabase{

    public abstract EmployeeDao employeeDao();
    public abstract EmployeeLeaveDao employeeLeaveDao();

    private static EmployeeDatabase INSTANCE;

    public static EmployeeDatabase getDatabase(final Context context) {
        if(INSTANCE == null){
            synchronized (EmployeeDatabase.class){
                INSTANCE = Room.databaseBuilder(context,EmployeeDatabase.class,"employee_database").build();
            }
        }
        return INSTANCE;
    }
}
