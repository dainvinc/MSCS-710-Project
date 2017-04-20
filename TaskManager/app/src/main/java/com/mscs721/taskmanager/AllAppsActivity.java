package com.mscs721.taskmanager;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by visha on 3/24/2017.
 */

public class AllAppsActivity extends Activity{

    ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_apps);

        setTitle("All Apps");
        lv = (ListView) findViewById(R.id.listview1);

        List<String> installedApps = getInstalledApps();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,installedApps );

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
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }
}
