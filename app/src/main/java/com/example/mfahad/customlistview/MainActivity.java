package com.example.mfahad.customlistview;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private EditText firstName;
    private EditText lastName;
    private EditText designation;
    private Button insert;
    private ArrayList<Employee> arrayList;
    private ListView listView;
    private CustomAdapter customAdapter;
    public Employee emp;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        firstName = (EditText)findViewById(R.id.fname);
        lastName = (EditText)findViewById(R.id.lname);
        designation =(EditText)findViewById(R.id.designation);
        insert = (Button)findViewById(R.id.insert);
        listView = (ListView)findViewById(R.id.listView);
        relativeLayout = (RelativeLayout) findViewById(R.id.main);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });
        arrayList = new ArrayList<Employee>();
        emp = new Employee();
        emp.setFirstName("Asad");
        emp.setDesignation("Manager");
        emp.setLastName("Saad");
        arrayList.add(emp);

        customAdapter = new CustomAdapter(this, R.layout.list_view_item , arrayList);
        listView.setAdapter(customAdapter);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstName.getText().toString().trim().equals("") || lastName.getText().toString().equals("")
                        ||designation.getText().toString().trim().equals("") ){
                    Toast.makeText(MainActivity.this, "All Feilds are required", Toast.LENGTH_SHORT).show();
                }else{
                    Employee emp = new Employee();
                    emp.setFirstName(firstName.getText().toString());
                    emp.setLastName(lastName.getText().toString());
                    emp.setDesignation(designation.getText().toString());
                    arrayList.add(emp);
                    customAdapter.notifyDataSetChanged();
    
                    firstName.setText("");
                    lastName.setText("");
                    designation.setText("");
                    addNotification();
                    Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    // Code for notifications
    public void addNotification(){
        NotificationCompat.Builder builder =
                (android.support.v7.app.NotificationCompat.Builder)new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("CustomListView")
                        .setContentText("Data Added");
        builder.setVibrate(new long[]{1000 ,1000 ,1000 , 1000 , 1000});
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }

    public void hideKeyboard()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
