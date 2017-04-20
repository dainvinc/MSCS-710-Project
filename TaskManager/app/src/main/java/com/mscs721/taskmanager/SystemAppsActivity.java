package com.mscs721.taskmanager;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by visha on 3/25/2017.
 */

public class SystemAppsActivity extends Activity {

    ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_apps);

        setTitle("System Apps");
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
            if ((isSystemPackage(p) == true)) {
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
