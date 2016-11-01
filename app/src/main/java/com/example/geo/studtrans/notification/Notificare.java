package com.example.geo.studtrans.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.geo.studtrans.R;

/**
 * Created by Geo on 1/11/2015.
 */
public class Notificare extends Activity {

    NotificationManager NM;
    EditText one,two,three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificare_layout);
        one = (EditText)findViewById(R.id.editText1);
        two = (EditText)findViewById(R.id.editText2);
        three = (EditText)findViewById(R.id.editText3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @SuppressWarnings("deprecation")
    public void notify(View vobj){
        String title = one.getText().toString();
        String subject = two.getText().toString();
        String body = three.getText().toString();
        NM=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification(android.R.drawable.
                stat_notify_more,title,System.currentTimeMillis());
        PendingIntent pending=PendingIntent.getActivity(
                getApplicationContext(),0, new Intent(),0);
        notify.setLatestEventInfo(getApplicationContext(),subject,body,pending);
        NM.notify(0, notify);
    }
}
