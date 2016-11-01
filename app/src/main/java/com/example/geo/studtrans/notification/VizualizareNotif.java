package com.example.geo.studtrans.notification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.geo.studtrans.R;
import com.example.geo.studtrans.main.Login;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Geo on 1/13/2015.
 */
public class VizualizareNotif extends Activity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.vizualizare_notif_layout);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listView);

        // Defined Array values to show in ListView

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data



        // Assign adapter to ListView
        getNotificari(listView);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index

            }

        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_gps,menu);
        return true;
    }




    @Override
    public boolean onMenuItemSelected(int panel, MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.notifii:
                Toast.makeText(VizualizareNotif.this, "Add Notification", Toast.LENGTH_LONG).show();
                Intent actV=new Intent(VizualizareNotif.this,AddNoticare.class);
                startActivity(actV);
                break;

        }

        return true;
    }




    private void getNotificari(final ListView listView) {
        Parse.initialize(this, "MxaJXEypSN95Yq2Jk5zt4Cx19JL5LVfLJTPlXFO6", "4OHqg9WWzi3qDTkHap9FjfnJSQ8rsAGvghA1VBUa");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Notificare");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> notificari, ParseException e) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(VizualizareNotif.this,
                        android.R.layout.simple_list_item_1);
                if (e == null) {
                    if (notificari != null) {
                        for (ParseObject notificare : notificari) {
                         if (Login.getUserFavorit().contains(notificare.get("NR_BUS"))) {
                             adapter.add((String) notificare.get("MESAJ"));
                         }
                        }
                    }
                } else {
                    e.printStackTrace();
                }
                listView.setAdapter(adapter);
            }
        });
    }






}
