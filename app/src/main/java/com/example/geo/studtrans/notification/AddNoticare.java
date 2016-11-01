package com.example.geo.studtrans.notification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geo.studtrans.R;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Geo on 1/13/2015.
 */
public class AddNoticare extends Activity{
    protected EditText nrAutobuz;
    protected EditText numeStatie;
    protected EditText mesaj;

    protected Button sendButton;


    private ParseObject notif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_layout);


        nrAutobuz=(EditText)findViewById(R.id.editText1);
        numeStatie=(EditText)findViewById(R.id.editText2);
        mesaj=(EditText)findViewById(R.id.editText3);

        sendButton=(Button)findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nrAutobuz1=nrAutobuz.getText().toString();
                final String numeStatie1=numeStatie.getText().toString();
                final String mesaj1=mesaj.getText().toString();
                ParseObject user = new ParseObject("Notificare");
                if(mesaj.length()==0){
                    Toast.makeText(AddNoticare.this, "Empty !", Toast.LENGTH_LONG).show();
                return ;
                }
                user.put("NR_BUS",nrAutobuz1);
                user.put("NUME_STATIE",numeStatie1);
                user.put("MESAJ",mesaj1);
                user.saveInBackground();
                Toast.makeText(AddNoticare.this, "Succed", Toast.LENGTH_LONG).show();
                Intent aaaa=new Intent(AddNoticare.this,VizualizareNotif.class);
                startActivity(aaaa);




            }
        });



    }


    public boolean extractDB(String nrAutobuz1, String numeStatie1) {

        final int[] var = new int[1];

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Notificare");
        query.whereEqualTo("NR_BUS", nrAutobuz);
        query.whereEqualTo("NUME_STATIE", numeStatie);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> users, com.parse.ParseException e) {
                if (e == null) {
                    // your logic here

                    if (users.isEmpty() ) {
                        // Toast.makeText(Register.this, "Va rog sa introduceti datele cerute ", Toast.LENGTH_SHORT).show();
                        var[0] =1;


                    } else { // login successful
                        // user = users.get(0);
                        var[0] =2;

                    }


                } else {
                    // handle Parse Exception here
                }
            }
        });

        if (var[0]==1)
        {
            return false;
        } else
        {
            return true;
        }

    }

}


