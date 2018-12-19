package com.zepeng.photogallery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartupReceiver extends BroadcastReceiver {
    private static final String TAG = "StartupReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: "+intent.getAction());
        boolean isOn = QueryPreferences.getIsAlarmOn(context);

        PollService.setServiceAlarm(context,isOn);
    }
}
