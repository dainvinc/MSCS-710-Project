package com.mscs721.taskmanager;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by visha on 3/25/2017.
 */

public class AppDetail extends Activity {

    ListView lv;
    TextView app;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_detail);

        //new AlertDialog.Builder(AppsActivity.this).setMessage(apps.toString()).show();

        setTitle("App Details");

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("app");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("app");
        }

        Toast.makeText(getApplicationContext(), "App: " + newString, Toast.LENGTH_SHORT).show();

        app = (TextView) this.findViewById(R.id.app);
        app.setText(newString);


    }


}
