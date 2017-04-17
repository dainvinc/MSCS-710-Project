package com.mscs721.taskmanager;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

/**
 * Created by visha on 3/24/2017.
 */

public class AllAppsActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@");
        setContentView(R.layout.all_apps);

        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> recentTasks = activityManager.getRunningAppProcesses();

        for (int i = 0; i < recentTasks.size(); i++)
        {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Log.d("Executed app", "Application executed : " +recentTasks+ "\t\t ID: "+recentTasks.get(i)+"");
            System.out.println("*********************************************");
            new AlertDialog.Builder(AllAppsActivity.this).setMessage(recentTasks.toString()).show();
        }
//        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
//        am.getMemoryInfo(info);
//        System.out.println(info);
//        ActivityManager.RecentTaskInfo task = am.getRecentTasks(1, 0).get(0);
//        startActivity(task.baseIntent);
    }
}
