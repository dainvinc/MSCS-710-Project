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
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class AllAppsActivity extends Activity{

    private ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_apps);
        
        // Setting Up the title 
        setTitle("All Apps");
        lv = (ListView) findViewById(R.id.listview1);

        // Creating a list of strings
        List<String> installedApps = getInstalledApps();
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,installedApps);
        
        // Setting the data behind the list view
        lv.setAdapter(arrayAdapter);
    }
    private List<String> getInstalledApps() {
        List<String> res = new ArrayList<String>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if (true) {
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                //Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                //res.add(new AppList(appName, icon));
                res.add(new String(appName));
            }
        }
        return res;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }
}
