package com.example.oscarplaza.stride;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Splash extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
//Remove notification bar
       this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView im = (ImageView)findViewById(R.id.imgLogo);
                im.setVisibility(View.GONE);
                TextView tx = (TextView)findViewById(R.id.presentation);
                tx.setVisibility(View.VISIBLE);


                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        new  Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TextView tx = (TextView)findViewById(R.id.presentation);
                                tx.setVisibility(View.GONE);
                                TextView tx2 = (TextView)findViewById(R.id.presentation2);
                                tx2.setVisibility(View.VISIBLE);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ExecuteRedirect();

                                    }
                                },1000);
                            }
                        },1000);

                    }

                    },1300);
                }
        }, 1000);





    }

    private void ExecuteRedirect() {
        Intent intent = new Intent(Splash.this, LoginActivity.class);
        startActivity(intent);
    }


}
