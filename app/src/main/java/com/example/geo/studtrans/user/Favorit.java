package com.example.geo.studtrans.user;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geo.studtrans.R;
import com.example.geo.studtrans.main.Login;
import com.parse.ParseObject;

/**
 * Created by Geo on 1/14/2015.
 */
public class Favorit extends Activity {

    public EditText nrBus;
    public Button addFavB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorit_layout);
        nrBus = ((EditText) findViewById(R.id.editText));
        addFavB = (Button) findViewById(R.id.Addbutton);
        addFavB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String numar = nrBus.getText().toString();
                ParseObject user = new ParseObject("Favorit");
                user.put("username", Login.usernameLogged);
                user.put("numar_bus", numar);
                user.saveInBackground();
                Login.updateFavorit();
                Toast.makeText(Favorit.this, "Succed", Toast.LENGTH_LONG).show();

            }
        });
    }
}
