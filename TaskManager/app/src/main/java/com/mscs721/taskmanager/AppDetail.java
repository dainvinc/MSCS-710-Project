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
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AppDetail extends Activity {

    private ListView lv;
    private TextView app;
    private Button uninstall;
    private String app_pack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_detail);
        setTitle("App Details");
        
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                app_pack= null;
            } else {
                app_pack= extras.getString("app");
            }
        } else {
            app_pack= (String) savedInstanceState.getSerializable("app");
        }

        Toast.makeText(getApplicationContext(), "App: " + app_pack, Toast.LENGTH_SHORT).show();

        app = (TextView) this.findViewById(R.id.app);
        uninstall = (Button) this.findViewById(R.id.uninstall);
        app.setText(app_pack);

        uninstall.setOnClickListener(myhandler1);

    }

    View.OnClickListener myhandler1 = new View.OnClickListener() {
        public void onClick(View v) {
            Uri packageUri = Uri.parse(app_pack);
            try {
                Intent uninstallIntent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri);
                startActivity(uninstallIntent);
            } catch (Exception e) {
            }
        }
    };




}
