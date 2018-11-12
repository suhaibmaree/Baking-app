package com.example.suhaib.bakingapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class WidgetService extends IntentService {

    public WidgetService() {
        super("s");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String integ = intent.getData().toString();
        Log.d(integ+"","Check integ adapter");

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] ids = appWidgetManager.getAppWidgetIds(new ComponentName(this.getPackageName(), BakingWidget.class.getName()));
        for (int i=0;i<ids.length;i++) {
            Log.d(ids[i]+integ+"","Check integ ids");
            BakingWidget.updateAppWidget(this, appWidgetManager, ids[i], integ);
        }
    }
}
