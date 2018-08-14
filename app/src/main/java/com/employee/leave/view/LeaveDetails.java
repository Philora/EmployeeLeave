package com.employee.leave.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.employee.leave.R;
import com.employee.leave.model.EmpLeaveAdapter;
import com.employee.leave.model.EmployeeLeaveEntity;
import com.employee.leave.viewmodel.EmployeeViewModel;

import java.util.ArrayList;
import java.util.List;

public class LeaveDetails extends AppCompatActivity{
    private ActionBar actionBar;

    private EmployeeViewModel empViewModel;
    private EmpLeaveAdapter empLeaveAdapter;
    private RecyclerView rec_employee_leave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave_details);
        setUpActionBar();
        empViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        rec_employee_leave = findViewById(R.id.leave_recycler_view);
        empLeaveAdapter = new EmpLeaveAdapter(this, new ArrayList<EmployeeLeaveEntity>() );
        rec_employee_leave.setLayoutManager(new LinearLayoutManager(this));
        rec_employee_leave.setAdapter(empLeaveAdapter);

        empViewModel.getEmployeeLeave().observe(this, new Observer<List<EmployeeLeaveEntity>>() {

            @Override
            public void onChanged(@Nullable List<EmployeeLeaveEntity> empLeaves) {
                empLeaveAdapter.setEmpLeaveList(empLeaves);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void setUpActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
}
