package com.example.shoaib.connection;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SHOAIB on 8/23/2017.
 */
class connect extends Thread {
   double latitude;
    double langitude;

    connect(double lang,double lat){
    this.latitude=lat;
    this.langitude=lang;
    }
    static HttpURLConnection urlConnection;
    static URL url = null;

    public void run() {

        try {
            url = new URL("http://192.168.10.7/InsertLangLat.php?langitude=" + langitude + "&latitude=" +latitude);

            // url = new URL("http://192.168.8.105/InsertLangLat.php?langitude=" +2222 + "&latitude=" +2222);

            urlConnection = (HttpURLConnection) url.openConnection();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
