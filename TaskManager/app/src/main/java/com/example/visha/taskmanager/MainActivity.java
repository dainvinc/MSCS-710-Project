package com.mscs721.taskmanager;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mscs721.taskmanager.AppsActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mFreeMemMessage;
    private TextView mTotalMemMessage;
    private Button allButton, appsButton, systemButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long totalMemory = mi.totalMem/1048576L;
        long availableMem = mi.availMem/1048576L;

        mFreeMemMessage = (TextView) findViewById(R.id.freeMemory);
        mTotalMemMessage = (TextView) findViewById(R.id.totalMemory);

        mTotalMemMessage.setText("Total Memory: " +String.valueOf(totalMemory) +" MB");
        mFreeMemMessage.setText("Free Memory: " +String.valueOf(availableMem)+" MB");


        allButton = (Button) findViewById(R.id.all);
        appsButton = (Button) findViewById(R.id.apps);
        systemButton = (Button) findViewById(R.id.system);

        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allAppScreen = new Intent(MainActivity.this, AllAppsActivity.class);
                startActivity(allAppScreen);
            }
        });

        appsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appsScreen = new Intent(MainActivity.this, AppsActivity.class);
                startActivity(appsScreen);
            }
        });

        systemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent systemScreen = new Intent(MainActivity.this, SystemAppsActivity.class);
                startActivity(systemScreen);
            }
        });
    }

}
