package co.edu.sena.bookingsena;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    private ProgressBar progressBar;
    private static final long SPLASH_SCREEN_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

            progressBar = (ProgressBar) findViewById(R.id.progressBar);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


            new Thread(new Runnable() {
                @Override
                public void run() {
                    doWork();
                    starApp();
                    finish();
                }
            }).start();
        }

    private void doWork() {
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        progressBar.setVisibility(View.VISIBLE);

        for ( int progress=0; progress<60; progress+=20){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void starApp() {
        Intent intentSplash = new Intent().setClass(
                Splash.this,LoginActivity.class);
        startActivity(intentSplash);
    }
}