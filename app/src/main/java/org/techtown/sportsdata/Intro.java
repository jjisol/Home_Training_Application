package org.techtown.sportsdata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by user on 2018-11-08.
 */

public class Intro extends AppCompatActivity {
    Intent intent;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(Intro.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

}
