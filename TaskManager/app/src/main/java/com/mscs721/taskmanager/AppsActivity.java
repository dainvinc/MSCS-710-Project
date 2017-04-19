package com.mscs721.taskmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.List;

/**
 * Created by visha on 3/25/2017.
 */

public class AppsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_apps);

        PackageManager pm = getPackageManager();
        List<ApplicationInfo> apps = pm.getInstalledApplications(0);

        new AlertDialog.Builder(AppsActivity.this).setMessage(apps.toString()).show();
        
        lv = (ListView) findViewById(R.id.your_list_view_id);

         // Instanciating an array list (you don't need to do this, 
         // you already have yours).
         List<String> array_list = new ArrayList<String>();
         array_list.add("App Name");
         //array_list.add("bar");

         // This is the array adapter, it takes the context of the activity as a 
         // first parameter, the type of list view as a second parameter and your 
         // array as a third parameter.
         ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.list_apps,array_list );

         lv.setAdapter(arrayAdapter); 

    }
}
