package com.app.fpestaffschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Main extends AppCompatActivity {

    FloatingActionButton student;
    ImageButton home,notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.setTitle("Dashboard");
        student = findViewById(R.id.student);
        home = findViewById(R.id.home);
        notification = findViewById(R.id.notification);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Dashboard()).addToBackStack(null).commit();

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new Students()).addToBackStack(null).commit();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new Dashboard()).addToBackStack(null).commit();
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new Notifications()).addToBackStack(null).commit();
            }
        });
    }
}