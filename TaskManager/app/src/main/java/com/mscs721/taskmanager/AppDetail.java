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
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AppDetail extends Activity {

    private ListView lv;
    private TextView app;
    private Button uninstall;
    private Button killAppButton;
    private String app_name;
    private int UNINSTALL_REQUEST_CODE = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_detail);
        setTitle("App Details");
        
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                app_name = null;
            } else {
                app_name = extras.getString("app");
                System.out.println("$$$$$$$$$ "+app_name);
            }
        } else {
            app_name = (String) savedInstanceState.getSerializable("app");
        }

        Toast.makeText(getApplicationContext(), "App: " + app_name, Toast.LENGTH_SHORT).show();

        app = (TextView) this.findViewById(R.id.apptitle);
        killAppButton = (Button) findViewById(R.id.button3);
        killAppButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        uninstall = (Button) this.findViewById(R.id.button4);
        app.setText(app_name);


        final ArrayList<PackageInfo> app_pack = new ArrayList<>();
        PackageManager pm = getApplicationContext().getPackageManager();
        final List<PackageInfo> packs = pm.getInstalledPackages(0);
        uninstall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri packageUri = Uri.parse("package:"+getApplicationContext().getPackageName());
                System.out.println("------ "+ getApplicationContext().getPackageName());
                try {
                    Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageUri);
                    //uninstallIntent.setData(Uri.parse(app_pack));
                    //uninstallIntent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
                    startActivity(uninstallIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UNINSTALL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d("TAG", "onActivityResult: user accepted the (un)install");
            } else if (resultCode == RESULT_CANCELED) {
                System.out.println("++++++++ "+resultCode + " "+RESULT_CANCELED);
                Log.d("TAG", "onActivityResult: user canceled the (un)install");
            } else if (resultCode == RESULT_FIRST_USER) {
                Log.d("TAG", "onActivityResult: failed to (un)install");
            }
        }
    }
//    View.OnClickListener myhandler1 = new View.OnClickListener() {
//        public void onClick(View v) {
//            Uri packageUri = Uri.parse(app_pack);
//            try {
//                Intent uninstallIntent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri);
//                startActivity(uninstallIntent);
//            } catch (Exception e) {
//            }
//        }
//    };




}
