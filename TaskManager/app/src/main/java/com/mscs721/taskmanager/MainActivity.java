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
    private PieChart pieMemChart;
    private PieChart pieCpuChart;
    private ArrayList<Entry> memEntries;
    private ArrayList<Entry> cpuEntries;
    private ArrayList<String> memLabels;
    private ArrayList<String> cpuLabels;
    private PieDataSet pieMemDataSet;
    private PieDataSet pieCpuDataSet;
    private PieData pieMemData;
    private PieData pieCpuData;
    private long availableMem;
    private long totalMemory;
    private long freeMem;
    private int usedCpu;
    private int freeCpu;

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

        readCpuUsage();

        // Creates a view for a pie chart
        pieMemChart = (PieChart) findViewById(R.id.memoryChart);
        pieCpuChart = (PieChart) findViewById(R.id.cpuChart);


        memEntries = new ArrayList<>();
        cpuEntries = new ArrayList<>();

        memLabels = new ArrayList<>();
        cpuLabels = new ArrayList<>();

        addValuesToMemPieChart();
        addValuesToCpuPieChart();

        addValuesToMemLabels();
        addValuesToCpuLabels();

        // Assigning data to the Pie Chart
        pieMemDataSet = new PieDataSet(memEntries, "");
        pieCpuDataSet = new PieDataSet(cpuEntries, "");

        pieMemData = new PieData(memLabels, pieMemDataSet);
        pieCpuData = new PieData(cpuLabels, pieCpuDataSet);

        pieMemDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieCpuDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        pieMemChart.setData(pieMemData);
        pieCpuChart.setData(pieCpuData);
        
//        mFreeMemMessage = (TextView) findViewById(R.id.freeMemory);
//        mTotalMemMessage = (TextView) findViewById(R.id.totalMemory);
//
//        mTotalMemMessage.setText("Total Memory: " +String.valueOf(totalMemory) +" MB");
//        mFreeMemMessage.setText("Free Memory: " +String.valueOf(availableMem)+" MB");

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

//        cpuTxt = (TextView) this.findViewById(R.id.cpuusage);
//        cpuTxt.setText("CPU usage: "+readUsage());
    }

    // Adding Values to the Pie Chart
    public void addValuesToMemPieChart() {
        memEntries.add(new BarEntry(freeMem, 0));
        memEntries.add(new BarEntry(availableMem, 1));
    }

    public void addValuesToCpuPieChart() {
        cpuEntries.add(new BarEntry(freeCpu, 0));
        cpuEntries.add(new BarEntry(usedCpu, 1));
    }

    // Adding the Labels
    public void addValuesToMemLabels() {
        //labels.add("Total Memory");
        memLabels.add("Used Memory");
        memLabels.add("Free Memory");
    }

    public void addValuesToCpuLabels() {
        cpuLabels.add("CPU Usage");
        cpuLabels.add("Total CPU");
    }

    // Calculating the CPU usage
    public int readCpuUsage() {
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

            float usedCpuFl = (float) (cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1));
            System.out.println("*********************"+usedCpuFl);
            usedCpu = (int) Math.ceil((1 - usedCpuFl)*100);
            System.out.println("*********************"+usedCpu);
            freeCpu = 100 - usedCpu;
            return usedCpu;

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
