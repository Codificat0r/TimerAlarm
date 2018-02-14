package com.example.timeralarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by usuario on 14/02/18.
 */

public class TemporizadorIntent extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent newActivity = new Intent(context, TimerAlarmActivity.class);
        newActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(newActivity);
    }

}
