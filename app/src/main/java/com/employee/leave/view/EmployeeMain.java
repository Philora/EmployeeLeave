package com.employee.leave.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.employee.leave.R;
import com.employee.leave.model.Employee;
import com.employee.leave.model.EmployeeAdapter;
import com.employee.leave.viewmodel.EmployeeViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeMain extends AppCompatActivity {
    private final String TAG = "EmployeeMain";
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    List<Employee> employeeList;
    EmployeeAdapter adapter;
    EmployeeViewModel employeeViewModel;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_main);

        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        employeeList = new ArrayList<Employee>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EmployeeAdapter(EmployeeMain.this, employeeList);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        requestQueue = Volley.newRequestQueue(this);

        mvcCall();
      //  mvvmCall();
    }

    private void mvcCall() {
        String json_url = "https://api.myjson.com/bins/78tlm";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(json_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "onResponse: " + response);

                employeeList = Arrays.asList(gson.fromJson(response.toString(), Employee[].class));
                adapter = new EmployeeAdapter(EmployeeMain.this, employeeList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void mvvmCall() {
        employeeViewModel.employeeList();

        employeeViewModel.getEmployee().observeForever(new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                adapter.setEmpList(employees);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.leave_option:
                startActivity(new Intent(this, LeaveDetails.class));
        }
        return true;
    }
}