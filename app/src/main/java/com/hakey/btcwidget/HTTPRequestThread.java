package com.hakey.btcwidget;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 29.11.16.
 */

public class HTTPRequestThread extends Thread{
    private static final String urlString = "https://btc-e.nz/api/3/ticker/btc_usd";

    public String getPriceString() {
        return output;
    }

    private String output;


    private void requestPrice() {


        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            output = response.toString();
        } catch (Exception e) {
            output = e.toString();
        }
    }

    @Override
    public void run() {
        requestPrice();
    }
}
