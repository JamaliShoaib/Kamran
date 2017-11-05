package com.example.shoaib.connection;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends  AppCompatActivity {
    Button btnShowLocation;
    static HttpURLConnection urlConnection;
    static URL url = null;

    // GPSTracker class
    GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(MainActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    senddata(latitude,longitude);

                    // \n is for new line
                   // Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();


                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });
    }
    double lat;
    double lang;
    public void senddata(double langitude, double latitude) {
        this.lat = latitude;
        this.lang = langitude;

        Log.i("into the ", ".......................................... ");

        Thread t = new Thread() {
            public void run() {
                try {
                    url = new URL("http://192.168.10.7/InsertLangLat.php?langitude=" + 1111 + "&latitude=" +111);

                    //       url = new URL("http://192.168.10.7/update.php?langitude=" + lang + "&latitude=" + lat + "&id=" + 1);

                    Log.i("into the ", " run method  of connect classs");

                    // url = new URL("http://192.168.8.105/InsertLangLat.php?langitude=" +2222 + "&latitude=" +2222);

                    urlConnection = (HttpURLConnection) url.openConnection();

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("errrrrrrrrrr",""+e);
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }


        };t.start();
    }
}