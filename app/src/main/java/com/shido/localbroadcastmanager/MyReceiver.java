package com.shido.localbroadcastmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int a = intent.getIntExtra("a", 0);
        int b = intent.getIntExtra("b", 0);

        int result = a + b;

        //Retornar o resultado para a main activity
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        Intent returningIntent = new Intent("my.result.intent");
        returningIntent.putExtra("result", result);
        localBroadcastManager.sendBroadcast(returningIntent);

    }


}
