package com.example.geo.studtrans.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geo.studtrans.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;

/**
 * Created by Geo on 12/4/2014.
 */
public class Update extends Activity {

    protected EditText updateEmail;
    protected EditText updateOldPassword;
    protected EditText updateNewPassword;
    protected Button updateB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_layout);

        updateEmail = (EditText) findViewById(R.id.txtEmailUpdate);
        updateOldPassword = (EditText) findViewById(R.id.txtOldPassword);
        updateNewPassword = (EditText) findViewById(R.id.txtNewPassword);

        updateB = (Button) findViewById(R.id.buttonUpdate);
        updateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = updateEmail.getText().toString();
                final String oldPass = updateOldPassword.getText().toString();
                final String newPass = updateNewPassword.getText().toString();


                ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
                query.whereEqualTo("username", Login.usernameLogged);
                query.whereEqualTo("password", oldPass);
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> users, ParseException e) {
                        if (e == null) {
                            if (users.isEmpty()) {
                                Toast.makeText(Update.this, "Invalid password", Toast.LENGTH_SHORT).show();
                            } else { //successfuly logged
                                if(newPass!=null) {
                                    ParseObject user = users.get(0);
                                    user.put("Email", email);
                                    user.put("password", newPass);
                                    user.saveInBackground();
                                    Toast.makeText(Update.this, "User updated!", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(Update.this, "Invalid password!", Toast.LENGTH_SHORT).show();
                                }

                            }


                        } else {
                            // handle Parse Exception here
                        }
                    }
                });


            }

        });


    }
}


