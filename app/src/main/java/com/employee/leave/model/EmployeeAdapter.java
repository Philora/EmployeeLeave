package com.employee.leave.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.employee.leave.R;
import com.employee.leave.view.LeaveActivity;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>{

    private List<Employee> empList;
    private Context context;

    public EmployeeAdapter(Context context, List<Employee> employee) {
        this.context = context;
        this.empList = employee;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Employee employee = empList.get(position);
        Glide.with(context).load(employee.getEmpProfile()).into(holder.imageview);

        holder.name.setText(employee.getEmpName());
        holder.age.setText(employee.getEmpAge());
        holder.gender.setText(employee.getEmpGender());
        holder.designation.setText(employee.getEmpDesignation());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), LeaveActivity.class);
                i.putExtra("emp_image", holder.imageview.toString());
                i.putExtra("emp_name",holder.name.getText().toString());
                i.putExtra("emp_age",holder.age.getText().toString());
                i.putExtra("emp_gender",holder.gender.getText().toString());
                i.putExtra("emp_designation",holder.designation.getText().toString());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return empList.size();
    }

    public void setEmpList(List<Employee> employees) {
        this.empList = employees;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemClickListener{

        private TextView name, age, gender, designation;
        private ImageView imageview;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.profile_image);
            name = (TextView) itemView.findViewById(R.id.text_Name);
            age = (TextView) itemView.findViewById(R.id.text_Age);
            gender = (TextView) itemView.findViewById(R.id.text_Gender);
            designation = (TextView) itemView.findViewById(R.id.text_Designation);
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "User's Name : " + empList.get(getAdapterPosition()).getEmpName(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }

}
