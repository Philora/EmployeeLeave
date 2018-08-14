package com.employee.leave.model;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.employee.leave.R;
import com.employee.leave.viewmodel.EmployeeViewModel;

import java.util.ArrayList;
import java.util.List;

public class EmpLeaveAdapter extends RecyclerView.Adapter<EmpLeaveAdapter.MyViewHolder> {
    Context context;
    private List<EmployeeLeaveEntity> empLeaveList;

    public EmpLeaveAdapter(Context context, ArrayList<EmployeeLeaveEntity> empLeaves ) {
        this.empLeaveList = empLeaves;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_leave_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final EmployeeLeaveEntity empLeaves = empLeaveList.get(position);
        holder.empName.setText(empLeaves.getEmpMName());
        holder.empLeaveFrom.setText(empLeaves.getEmpMLeaveFrom());
        holder.empLeaveTo.setText(empLeaves.getEmpMLeaveTo());
    }


    @Override
    public int getItemCount() {
        return empLeaveList.size();
    }

    public void setEmpLeaveList(List<EmployeeLeaveEntity> empLeaveList) {
        this.empLeaveList = empLeaveList;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView empName, empLeaveFrom, empLeaveTo;

        public MyViewHolder(View view) {
            super(view);
            empName = (TextView) view.findViewById(R.id.txt_EmpLeaveName);
            empLeaveFrom = (TextView) view.findViewById(R.id.txt_EmpLeaveFrom);
            empLeaveTo = (TextView) view.findViewById(R.id.txt_EmpLeaveTo);
        }
    }

}
