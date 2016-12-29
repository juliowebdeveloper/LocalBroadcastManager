package com.shido.localbroadcastmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
    }


    public void sendNormalBroadcast(View view){
        Intent i = new Intent(this, MyReceiver.class );
        i.putExtra("a", 10);
        i.putExtra("b", 20);
        sendBroadcast(i);

    }


    @Override
    protected void onResume() {
        super.onResume();
        localBroadcastManager.registerReceiver(resultReceiver, new IntentFilter("my.result.intent")); //Action que vem do MYReceiver para permitir a comunicação
    }

    @Override
    protected void onPause() {
        super.onPause();
        localBroadcastManager.unregisterReceiver(resultReceiver);
    }

    //LocalBroadCast receiver - Permite a comunicação segura entre dois componentes da aplicação:
    //activity e services ou broadcast receivers e afins, esta confinado dentro do escopo do app - não tem vazamento de informação
    public BroadcastReceiver resultReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Soma que o MyReceiver enviou via LocalBroadcastManager
            Toast.makeText(context, "Result receive" + String.valueOf(intent.getIntExtra("result", 0)), Toast.LENGTH_SHORT).show();
        }
    };
}
