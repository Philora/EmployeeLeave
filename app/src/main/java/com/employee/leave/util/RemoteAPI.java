package com.employee.leave.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.employee.leave.model.Employee;
import com.employee.leave.model.EmployeeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;

public class RemoteAPI {
    private static final String TAG = "RemoteAPI";
    Context context;
    List<Employee> employeeList;
    EmployeeAdapter adapter;
    private Gson gson;
    private RequestQueue requestQueue;

    public RemoteAPI(Context context) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        this.context = context;
        gson = gsonBuilder.create();
        requestQueue = Volley.newRequestQueue(context);
        adapter = new EmployeeAdapter(context, employeeList);
    }

    public void emloyeeAPI() {
        /*String json_url = "https://api.myjson.com/bins/mwb3e";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(json_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "onResponse: " + response);
                employeeList = new ArrayList<Employee>();
                Employee Employee = gson.fromJson(response.toString(), Employee.class);
                Employee.getmId();
                Employee.getmImageURL();
                Employee.getmName();
                Employee.getmAge();
                Employee.getmGender();
                Employee.getmDesignation();
                employeeList.add(Employee);


                 *//*for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Log.d(TAG, "onResponse: " + jsonObject);
                        Employee employeeEntity = new Employee(jsonObject.getInt("emp_id"),jsonObject.getString("emp_image"),jsonObject.getString("emp_name"), jsonObject.getString("emp_age"), jsonObject.getString("emp_gender"), jsonObject.getString("emp_designation"));
                        employeeList.add(employeeEntity);
                    } catch (Exception e) {
                        Log.d(TAG, "onErrorResponse: " + e.getMessage());
                    }
                }*//*
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
            }
        });
        requestQueue.add(jsonArrayRequest);*/
        String json_url = "https://api.myjson.com/bins/78tlm";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(json_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "onResponse: " + response);

                employeeList = Arrays.asList(gson.fromJson(response.toString(), Employee[].class));
                adapter = new EmployeeAdapter(context, employeeList);
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
}
