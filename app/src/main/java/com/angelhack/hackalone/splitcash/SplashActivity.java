package com.angelhack.hackalone.splitcash;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new RequestTask(new RequestTask.AsyncResponse() {
            @Override
            public void processFinish(String output){

                Intent intent;
                Class object = LoginActivity.class;

                if (!output.equals("OK")) {
                    object = MainActivity.class;
                }

                intent = new Intent(getApplicationContext(), object);

                startActivity(intent);
                finish();
            }
        }).execute("https://split-cash-justhack.c9users.io/api/user");
    }
}