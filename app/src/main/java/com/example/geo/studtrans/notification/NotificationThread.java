package com.example.geo.studtrans.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.example.geo.studtrans.R;
import com.example.geo.studtrans.main.Login;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Geo on 1/13/2015.
 */
public class NotificationThread extends Thread {

    Context mContext;

    private static final int ID_NOTIFICATION = 101;
    private static final int HISTORY_TIME = 1;

    public NotificationThread(Context mContext) {
        this.mContext = mContext;
    }

    @Override

    public void run() {
       final List<Integer> data=new ArrayList<Integer>();
        data.add(0);

        while (true) {


            ParseQuery<ParseObject> query = ParseQuery.getQuery("Notificare");
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> notifications, ParseException e) {
                    if (e == null) {
                        if (notifications != null) {
                            for (ParseObject notification : notifications) {
                                if (isNotificationOld(notification)) {
                                    notification.deleteInBackground();
                                }
                            }
                            int csize = 0;
                            for (ParseObject notification : notifications) {
                                if (Login.getUserFavorit().contains(notification.get("NR_BUS"))){
                                    csize++;
                                }
                            }
                            int size=data.get(0);
                            data.set(0,csize);
                            NotificationManager mNotificationManager =
                                    (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                            if(csize==0){
                                mNotificationManager.cancel(ID_NOTIFICATION);
                            }else if(csize!=size) {
                                 NotificationCompat.Builder mBuilder =
                                         new NotificationCompat.Builder(mContext)
                                                 .setSmallIcon(R.drawable.common_signin_btn_icon_normal_dark)
                                                 .setContentTitle("Alerta in trafic!")
                                                 .setContentText("Exista " + csize+ " alerte de control in autobuze!");
                                 mNotificationManager.notify(ID_NOTIFICATION, mBuilder.build());
                             }
                            Login.updateFavorit();
                        }
                    } else {
                        e.printStackTrace();
                    }
                }
            });
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception ex) {

            }
        }

    }

    private boolean isNotificationOld(ParseObject notification) {
        return new Date().getTime() - notification.getCreatedAt().getTime() >= HISTORY_TIME*60*1000;
    }
}