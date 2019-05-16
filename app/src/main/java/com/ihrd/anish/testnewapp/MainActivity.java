package com.ihrd.anish.testnewapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
String s;
    AlertDialog ad;
    String address;

    MyCountDownTimer countDownTimer;
    private static final String FORMAT = "%02d:%02d:%02d";
    CountDownTimer c;
    static int timeval;
    private final long startTime = 10 * 1000;

    private final long interval = 1 * 1000;
    AlertDialog.Builder alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countDownTimer = new MyCountDownTimer(startTime, interval);



        showSettingsAlert();  // Call Dialog


        // For lat long to  Address Conversion ( Test Only )
        try{

        Geocoder geo = new Geocoder(MainActivity.this.getApplicationContext(), Locale.getDefault());
        List<Address> addresses = geo.getFromLocation(9.2251, 76.6785, 1);
        if (addresses.isEmpty()) {
            // e.setText("Waiting for Location");
            s="No Address";
        } else {
            if (addresses.size() > 0) {
                s = addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality() + ", " + addresses.get(0).getAdminArea();

            }
        }

            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    catch (Exception e1) {
        e1.printStackTrace(); // getFromLocation() may sometimes fail
    }




    }



    // Functions Start

    public void showSettingsAlert() {
        alertDialog = new AlertDialog.Builder(MainActivity.this);
        countDownTimer.start();

        // Setting Dialog Title
        alertDialog.setTitle("Safety alert!");

        // Setting Dialog Message
        alertDialog
                .setMessage(" Do you want to report?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(),"Hello Ok Clicked",Toast.LENGTH_LONG).show();
// Call API Here

                        countDownTimer.cancel();
                    }
                });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // dialogInterface=dialog;
                        countDownTimer.cancel();
                        dialog.cancel();
                    }
                });

        // Showing Alert Message
        // alertDialog.show();
        ad = alertDialog.create();
        ad.show();
    }



    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            Toast.makeText(getApplicationContext(),"Auto Cancelled",Toast.LENGTH_LONG).show();
// Call API Here

            ad.dismiss();




        }


        @Override

        public void onTick(long millisUntilFinished) {

            //millisUntilFinished / 1000;
        }

    }



    // Functions End


}
