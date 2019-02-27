package com.example.biggernumbergame.helpers;

import android.content.Context;
import android.content.Intent;

public class ActivityHelper {

    public static void changeActivity(Context context, Class activityClass) {
        Intent mainActivityIntent = new Intent(context, activityClass);
        context.startActivity(mainActivityIntent);
    }
}
