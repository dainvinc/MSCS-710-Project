/*********************************************************************
*   MSCS-710-Project
*   TaskManager
*   Created By  :   Bhargavi Madunala 
*                    Mitin Sharma
*                    Surya Kiran Akula
*                    Vishal Koosuri           
**********************************************************************/

package com.mscs721.taskmanager;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mFreeMemMessage;
    private TextView mTotalMemMessage, cpuTxt;
    private Button allButton, appsButton, systemButton;
    private PieChart pieChart;
    private ArrayList<Entry> entries;
    private ArrayList<String> labels;
    private PieDataSet pieDataSet;
    private PieData pieData;
    private long availableMem = 0;
    private long totalMemory = 0;
    private long freeMem = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieves the memory usage(RAM)
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        totalMemory = mi.totalMem/1048576L;
        availableMem = mi.availMem/1048576L;
        freeMem = totalMemory - availableMem;

        // Creates a view for a pie chart
        pieChart = (PieChart) findViewById(R.id.chart1);
        entries = new ArrayList<>();
        labels = new ArrayList<String>();
        addValuesToPieChart();
        addValuesToLabels();
        
        // Assigning data to the Pie Chart
        pieDataSet = new PieDataSet(entries, "");
        pieData = new PieData(labels, pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(pieData);
        
        mFreeMemMessage = (TextView) findViewById(R.id.freeMemory);
        mTotalMemMessage = (TextView) findViewById(R.id.totalMemory);

        mTotalMemMessage.setText("Total Memory: " +String.valueOf(totalMemory) +" MB");
        mFreeMemMessage.setText("Free Memory: " +String.valueOf(availableMem)+" MB");

        allButton = (Button) findViewById(R.id.all);
        appsButton = (Button) findViewById(R.id.apps);
        systemButton = (Button) findViewById(R.id.system);

        // Allows to respond when an item is clicked
        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allAppScreen = new Intent(MainActivity.this, AllAppsActivity.class);
                startActivity(allAppScreen);
            }
        });
        
        // Allows to respond when an item is clicked
        appsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // This method is called when the button is actually clicked or tapped
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

        cpuTxt = (TextView) this.findViewById(R.id.cpuusage);
        cpuTxt.setText("CPU usage: "+readUsage());
    }

    // Adding Values to the Pie Chart
    public void addValuesToPieChart() {
        entries.add(new BarEntry(totalMemory, 0));
        entries.add(new BarEntry(freeMem, 1));
        entries.add(new BarEntry(availableMem, 2));
    }

    // Adding the Labels
    public void addValuesToLabels() {
        labels.add("Total Memory");
        labels.add("Available Memory");
        labels.add("Free Memory");
    }
    // Calculating the CPU usage
    public float readUsage() {
        try {
            RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
            String load = reader.readLine();
            
            // Storing the values in string array tokens
            String[] toks = load.split(" ");
            long idle1 = Long.parseLong(toks[5]);
            long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            try {
                Thread.sleep(360);
            } catch (Exception e) {}

            reader.seek(0);
            load = reader.readLine();
            reader.close();
            
            // Storing the values in string array tokens
            toks = load.split(" ");
            long idle2 = Long.parseLong(toks[5]);
            long cpu2 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            return (float)(cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return 0;
    }
    // examine the cpu
    public int CpuRead(){
        return 1;
    }
}
