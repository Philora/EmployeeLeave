package com.employee.leave.view;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.employee.leave.R;
import com.employee.leave.model.Employee;
import com.employee.leave.model.EmployeeLeaveEntity;
import com.employee.leave.viewmodel.EmployeeViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LeaveActivity extends AppCompatActivity {
    Calendar startTime, endTime;
    TextView emp_Name, emp_Age, emp_Gender, emp_Designation, from_DateTime, to_DateTime;
    ImageView emp_Image;
    SimpleDateFormat dateTimeformat;
    EmployeeViewModel employeeViewModel;
    String empImage, empName, empAge, empGender, empDesignation;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave_activity);
        setUpActionBar();
        emp_Image = (ImageView) findViewById(R.id.emp_profile_image);
        emp_Name = (TextView) findViewById(R.id.text_Name);
        emp_Age = (TextView) findViewById(R.id.text_Age);
        emp_Gender = (TextView) findViewById(R.id.text_Gender);
        emp_Designation = (TextView) findViewById(R.id.text_Designation);
        from_DateTime = (TextView) findViewById(R.id.txt_From_DateTime);
        to_DateTime = (TextView) findViewById(R.id.txt_To_DateTime);
        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);

         empImage = getIntent().getStringExtra("emp_image");
         empName = getIntent().getStringExtra("emp_name");
         empAge = getIntent().getStringExtra("emp_age");
         empGender = getIntent().getStringExtra("emp_gender");
         empDesignation = getIntent().getStringExtra("emp_designation");

//        Glide.with(this).load(empImage).into(emp_Image);
        emp_Image.setImageDrawable(Drawable.createFromPath(empImage.toString()));
        emp_Name.setText("Name: " + empName);
        emp_Age.setText("Age: " + empAge);
        emp_Gender.setText("Gender: " + empGender);
        emp_Designation.setText("Designation: " + empDesignation);
        dateTimeformat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
    }

    public void fromDatetime(final View view) {
        final View dialogView = View.inflate(this, R.layout.date_time_alert, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        dialogView.findViewById(R.id.btn_DateTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
                TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);

                startTime = new GregorianCalendar(
                        datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute());
                int hour = startTime.get(Calendar.HOUR_OF_DAY);
                int minutes = startTime.get(Calendar.MINUTE);
                if (hour >= 9 && hour < 13) {
                    from_DateTime.setText("From: " + dateTimeformat.format(startTime.getTime()));
                } else if (hour >= 13 && minutes <= 30) {
                    from_DateTime.setText("From: " + dateTimeformat.format(startTime.getTime()));
                } else {
                    Toast.makeText(LeaveActivity.this, "Too Early not accepted for Half day", Toast.LENGTH_SHORT).show();
                    from_DateTime.setText("");
                }
                /*long milliseconds1 = startTime.getTimeInMillis();
                long milliseconds2 = endTime.getTimeInMillis();

                long diff = milliseconds2 - milliseconds1;
                long diffSeconds = diff / 1000;
                long diffMinutes = diff / (60 * 1000);
                long diffHours = diff / (60 * 60 * 1000);
                long diffDays = diff / (24 * 60 * 60 * 1000);
                int hours = (end - start) % 24;
                if(hours >= 4){
                    System.out.println(hours);
                } else {
                    Log.d(TAG, "onClick: ");
                }*/
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(dialogView);
        alertDialog.show();
    }

    public void toDatetime(View view) {
        final View dialogView = View.inflate(this, R.layout.date_time_alert, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        dialogView.findViewById(R.id.btn_DateTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
                TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);

                endTime = new GregorianCalendar(
                        datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute());
                int to_hour = endTime.get(Calendar.HOUR_OF_DAY);

                if (to_hour >= 13 && to_hour <= 17) {
                    to_DateTime.setText("To: " + dateTimeformat.format(endTime.getTime()));
                } else {
                    Toast.makeText(LeaveActivity.this, "End Time too early... Not accepted for Half day", Toast.LENGTH_SHORT).show();
                    to_DateTime.setText("");
                }
                if (startTime.compareTo(endTime) > 0) {

                }
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(dialogView);
        alertDialog.show();
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

    public void applyLeave(View view) {
       String mEmp_LFrom= from_DateTime.getText().toString().trim();
       String mEmp_LTo= to_DateTime.getText().toString().trim();
        if (employeeViewModel != null && !TextUtils.isEmpty(empName) && !TextUtils.isEmpty(mEmp_LFrom) && !TextUtils.isEmpty(mEmp_LTo)) {
            employeeViewModel.addEmpLeave(new EmployeeLeaveEntity(empName, mEmp_LFrom, mEmp_LTo));
            Toast.makeText(this, "Leave Applied", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        }
    }
}
