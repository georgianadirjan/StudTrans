package com.example.geo.studtrans.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geo.studtrans.user.Home;
import com.example.geo.studtrans.notification.NotificationThread;
import com.example.geo.studtrans.R;
import com.example.geo.studtrans.admin.LoginAdmin;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geo on 12/4/2014.
 */
public class Login extends Activity {


    private ParseObject user;
    private Button loginB;
    public static String usernameLogin;
    public static String passwordLogin;
    public static String usernameLogged="";
    private static List<String> userFavorit=new ArrayList<String>();

    public static void updateFavorit(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Favorit");
        query.whereEqualTo("username",usernameLogged);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> favorite, ParseException e) {
                if (e == null) {
                    if (favorite != null) {
                        userFavorit.clear();
                        for (ParseObject favorit : favorite) {
                            userFavorit.add((String) favorit.get("numar_bus"));
                        }
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        loginB = (Button) findViewById(R.id.buttonLogin);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailTxt = ((EditText) findViewById(R.id.txtEmail)).getText().toString();
                String passwordTxt = ((EditText) findViewById(R.id.txtPassword)).getText().toString();

                extractDB(emailTxt,passwordTxt);
            }
        });

    }


    public void extractDB(final String emailTxt, final String password) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
        query.whereEqualTo("Email", emailTxt);
        query.whereEqualTo("password", password);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> users, ParseException e) {
                if (e == null) {
                    // your logic here

                    if (users.isEmpty()) {
                        Toast.makeText(Login.this, "Please enter your Username and Password", Toast.LENGTH_SHORT).show();

                    } else { // login successful
                        user = users.get(0);

                        usernameLogin=emailTxt;
                        usernameLogged=emailTxt;
                        passwordLogin=password;

                        Intent logI = new Intent(Login.this, Home.class);
                        startActivity(logI);
                        new NotificationThread(Login.this).start();
                        updateFavorit();

                    }


                } else {
                    // handle Parse Exception here
                }
            }
        });


    }

    public static List<String> getUserFavorit() {
        return userFavorit;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.login_admin_menu,menu);
        return true;
    }




    @Override
    public boolean onMenuItemSelected(int panel, MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.admin:
                Toast.makeText(Login.this, "Login Admin", Toast.LENGTH_LONG).show();
                Intent aa=new Intent(Login.this,LoginAdmin.class);
                startActivity(aa);
                break;

        }

        return true;
    }


}