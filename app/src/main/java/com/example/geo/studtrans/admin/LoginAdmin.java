package com.example.geo.studtrans.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geo.studtrans.user.Home;
import com.example.geo.studtrans.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Geo on 1/15/2015.
 */
public class LoginAdmin extends Activity {

    public Button loginBAdmin;
    private ParseObject user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_admin_layout);

        loginBAdmin = (Button) findViewById(R.id.buttonLoginAdmin);
        loginBAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userNameAdmin = ((EditText) findViewById(R.id.txtUsername)).getText().toString();
                String passwordAdmin = ((EditText) findViewById(R.id.txtPassword)).getText().toString();

                extractDB(userNameAdmin, passwordAdmin);
            }
        });

    }


    public void extractDB(final String userNameAdmin, final String passwordAdmin) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Admin");
        query.whereEqualTo("AdminUsername", userNameAdmin);
        query.whereEqualTo("AdminPassword", passwordAdmin);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> users, ParseException e) {
                if (e == null) {
                    // your logic here

                    if (users.isEmpty()) {
                        Toast.makeText(LoginAdmin.this, "Please enter your Username and Password", Toast.LENGTH_SHORT).show();

                    } else { // login successful
                        user = users.get(0);



                        //  String uId = user.getObjectId();
                        Intent logI = new Intent(LoginAdmin.this, Home.class);
                        startActivity(logI);


                    }


                } else {
                    // handle Parse Exception here
                }
            }
        });


    }
}
