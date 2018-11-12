package com.example.suhaib.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidget extends AppWidgetProvider {
    public static String ingredian = "ingredians";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, String ingredians) {
        ingredian= ingredians;


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        views.setTextViewText(R.id.appwidget_text, ingredians);

        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        views.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId,views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds){
            updateAppWidget(context,appWidgetManager,appWidgetId,ingredian);
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

