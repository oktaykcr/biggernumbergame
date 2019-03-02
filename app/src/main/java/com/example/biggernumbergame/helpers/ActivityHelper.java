package com.example.biggernumbergame.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.biggernumbergame.R;

import java.util.Map;

public class ActivityHelper {

    public static void changeActivity(Context context, Class activityClass) {
        Intent activityIntent = new Intent(context, activityClass);
        context.startActivity(activityIntent);
        ((Activity)context).overridePendingTransition(R.anim.anim_fadein, R.anim.anim_fadeout);
    }

    public static void changeActivityWithStringData(Context context, Class activityClass, Map<String, String> data) {
        Intent activityIntent = new Intent(context, activityClass);

        for(String key : data.keySet()) {
            activityIntent.putExtra(key, data.get(key));
        }
        context.startActivity(activityIntent);
        ((Activity)context).overridePendingTransition(R.anim.anim_fadein, R.anim.anim_fadeout);
    }

    public static void changeActivityWithIntegerData(Context context, Class activityClass, Map<String, Integer> data) {
        Intent activityIntent = new Intent(context, activityClass);

        for(String key : data.keySet()) {
            activityIntent.putExtra(key, data.get(key));
        }
        context.startActivity(activityIntent);
        ((Activity)context).overridePendingTransition(R.anim.anim_fadein, R.anim.anim_fadeout);
    }
}
