package com.hakey.btcwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.net.ssl.HttpsURLConnection;

/**
 * Implementation of App Widget functionality.
 */
public class BTCwidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId)  {

        // Construct the RemoteViews object
        String output = "Wait for BTC price";

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.btcwidget);

        views.setTextViewText(R.id.appwidget_text, output);
        appWidgetManager.updateAppWidget(appWidgetId, views);

        HTTPRequestThread thread = new HTTPRequestThread();
        thread.start();
        try {
            while (true) {
                Thread.sleep(500);
                if(!thread.isAlive()) {
                    Calendar now = Calendar.getInstance();
                    output = "Price: " + JSONParser.getPrice(thread.getPriceString()) +
                    "\nTime: " + now.get(Calendar.HOUR_OF_DAY)
                            + ":" + now.get(Calendar.MINUTE);
                    break;
                }
            }

        } catch (Exception e) {
            output = e.toString();
        }


        views.setTextViewText(R.id.appwidget_text, output);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

            updateAppWidget(context, appWidgetManager, appWidgetId);

        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    
}

