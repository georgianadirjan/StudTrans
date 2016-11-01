package com.example.geo.studtrans.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.geo.studtrans.R;

/**
 * Created by Geo on 1/15/2015.
 */
public class Help extends Activity {
    public TextView helpTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);

        helpTxt=((TextView) findViewById(R.id.textView7));
        helpTxt.setText("      StudTrans reprezinta un mediu care va faciliteaza calatoriile in orasul Cluj-Napoca. Este o aplicatie usor de utilizat." +
                "                                 La deschiderea ei aveti posibilitatea de logare/inregistrare in baza de date, apoi veti fi redirectionat spre meniul principal Home " +
                "care va redirectioneaza in ferestrele dorite.                Aveti posibilitatea de a vizualiza notificari cu privire la control, creare o " +
                "notificare pentru restul utilizatorilor, o lista de trasee, posibilitatea setarii unuia favorit" +
                " si nu in ultimul rand updatarea contului. ");
        }
        }
