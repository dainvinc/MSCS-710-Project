/*********************************************************************
*   MSCS-710-Project
*   TaskManager
*   Created By  :   Bhargavi Madunala 
*                    Mithin Sharma
*                    Surya Kiran Akula
*                    Vishal Koosuri           
**********************************************************************/
package com.mscs721.taskmanager;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class AllAppsActivity extends Activity {

    private ListView lv;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Application is not loaded this method is called
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_apps);

        setTitle("System Apps");
        lv = (ListView) findViewById(R.id.listview1);
        final List<String> installedApps = getInstalledApps();

        // Creating new adapter to store the data
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, installedApps );

        // Setting the data behind the list
        lv.setAdapter(arrayAdapter);

        // Callback is invoked when an item in this AdapterView has been clicked.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Click ListItem Number " + position + ", id: "+id, Toast.LENGTH_SHORT).show();
                String s = arrayAdapter.getItem(position);
                Intent in = new Intent(getApplicationContext(), AppDetail.class);
                in.putExtra("app", s);
                //Toast.makeText(getApplicationContext(), "S: " + s, Toast.LENGTH_SHORT).show();
                startActivity(in);
            }
        });
    }



    private List<String> getInstalledApps() {
        List<String> res = new ArrayList<String>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                String packName = p.packageName.toString();
                //Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                //res.add(new AppList(appName, icon));
                res.add(packName);
        }
        return res;
    }
}
