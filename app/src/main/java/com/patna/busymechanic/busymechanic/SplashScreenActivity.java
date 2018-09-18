package com.patna.busymechanic.busymechanic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Random;

public class SplashScreenActivity extends AppCompatActivity {

//    private int SLEEP_TIMER = 3;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        setContentView(R.layout.activity_splash_screen);
//
//
//        LogoLauncher logolauncher = new LogoLauncher();
//        logolauncher.start();
//    }
//
//    private class LogoLauncher extends Thread{
//          public void run(){
//              try{
//                  sleep(1000*SLEEP_TIMER);
//              }catch(InterruptedException e){
//                  e.printStackTrace();
//              }
//
//              Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
//              startActivity(intent);
//              SplashScreenActivity.this.finish();
//
//          }
//    }

    Thread splashTread;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = (ImageView)findViewById(R.id.imageView2);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        imageView.setAnimation(animation);


        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashScreenActivity.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreenActivity.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashScreenActivity.this.finish();
                }

            }
        };
        splashTread.start();
    }
}
