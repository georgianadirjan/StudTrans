package com.example.geo.studtrans.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.geo.studtrans.R;
import com.parse.Parse;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connecting the app to Parse
        Parse.initialize(this, "MxaJXEypSN95Yq2Jk5zt4Cx19JL5LVfLJTPlXFO6", "4OHqg9WWzi3qDTkHap9FjfnJSQ8rsAGvghA1VBUa");



        Button loginButton=(Button)findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginI= new Intent(MainActivity.this, Login.class);
                startActivity(loginI);
            }
        });

        Button registerButton=(Button)findViewById(R.id.buttonRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerI= new Intent(MainActivity.this, Register.class);
                startActivity(registerI);
            }
        });

        Button helpButton=(Button)findViewById(R.id.buttonHelp);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Help", Toast.LENGTH_SHORT).show();
                Intent helpI= new Intent(MainActivity.this, Help.class);
                startActivity(helpI);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
