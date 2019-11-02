package com.katouji.project_dummy;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HomeActivity extends AppCompatActivity {
    private Button btnAbout;
    private final String CHANNEL_ID = "personal_notification";
    private final int NOTIFICATION_ID = 001;
    private WifiManager wifiManager;
    private TextView txtUser;
    private Button lstfilm;
//    private Button add;
    private Button btncrud;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        txtUser = (TextView) findViewById((R.id.txtUser));
        btnAbout = (Button) findViewById(R.id.btnAbout);

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityAbout();
            }
        });





        lstfilm = (Button) findViewById(R.id.lstfilm);
        lstfilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityFilm();
            }
        });



        btncrud = (Button) findViewById(R.id.btncrud);
        btncrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitycrud();
            }
        });


        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if(getIntent().getExtras()!=null){

            Bundle bundle = getIntent().getExtras();
            txtUser.setText(bundle.getString("dataUsername"));
        }else{

            txtUser.setText(getIntent().getStringExtra("dataUsername"));
        }

    }



    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(wifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiStateReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiStateReceiver);
    }

    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(wifiManager.EXTRA_WIFI_STATE,wifiManager.WIFI_STATE_UNKNOWN);

            switch (wifiStateExtra){
                case WifiManager.WIFI_STATE_ENABLED:
                    Toast.makeText(getApplicationContext(), "Terhubung...",Toast.LENGTH_SHORT).show();
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    Toast.makeText(getApplicationContext(), "Terputus...",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    public void openActivityAbout(){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);

    }


    public void loadFragmentOne(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment1 = fragmentManager.findFragmentByTag("fragOne");

        if (fragment1 == null) {
            fragment1 = new FragmentOne();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrag, fragment1, "fragOne");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void loadFragmentTwo(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment2 = fragmentManager.findFragmentByTag("fragTwo");

        if (fragment2 == null) {
            fragment2 = new FragmentTwo();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrag, fragment2, "fragTwo");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void displayNotification(View view) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_wifi);
        builder.setContentTitle("Wifi Notification");
        builder.setContentText("Wifi sedang Nyala");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }

    public void openActivityFilm(){
        Intent intent = new Intent(this, row.class);
        startActivity(intent);

    }


//    public void openActivityAdd(){
//        Intent intent = new Intent(this, add.class);
//        startActivity(intent);
//
//    }

    public void openActivitycrud() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }











}

