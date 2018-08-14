package com.employee.leave.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.employee.leave.model.Employee;
import com.employee.leave.model.EmployeeLeaveEntity;
import com.employee.leave.util.EmployeeDatabase;
import com.employee.leave.util.RemoteAPI;
import java.util.List;

public class EmployeeViewModel extends AndroidViewModel{
    RemoteAPI remoteAPI;
    private LiveData<List<Employee>> listLiveData;
    private LiveData<List<EmployeeLeaveEntity>> listLeaveLiveData;
    EmployeeDatabase employeeDatabase;

    public EmployeeViewModel(Application application) {
        super(application);
        remoteAPI = new RemoteAPI(getApplication());
        employeeDatabase = EmployeeDatabase.getDatabase(this.getApplication());
        listLiveData = employeeDatabase.employeeDao().getEmpList();
        listLeaveLiveData = employeeDatabase.employeeLeaveDao().getEmpLeaveList();
    }

    public void employeeList() {
        remoteAPI.emloyeeAPI();
    }

    public LiveData<List<Employee>> getEmployee() {
        return listLiveData;
    }

    public void addEmpLeave(EmployeeLeaveEntity employeeLeave) {
        new addEmpAsyncTask(employeeDatabase).execute(employeeLeave);
    }

    public LiveData<List<EmployeeLeaveEntity>> getEmployeeLeave() {
        return listLeaveLiveData;
    }

    private class addEmpAsyncTask extends AsyncTask<EmployeeLeaveEntity, Void, Void> {

        EmployeeDatabase empDatabase;

        public addEmpAsyncTask(EmployeeDatabase employeeDatabase) {
            empDatabase = employeeDatabase;
        }

        @Override
        protected Void doInBackground(EmployeeLeaveEntity... employeeLeaves) {
            empDatabase.employeeLeaveDao().addEmpLeave(employeeLeaves[0]);
            return null;
        }
    }
}
