package com.iavariav.root.joon.Rule;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.iavariav.root.joon.Helper.Config;
import com.iavariav.root.joon.LoginActivity;
import com.iavariav.root.joon.R;

public class SplashScreenActivity extends AppCompatActivity {

    private int MY_PERMISSIONS_REQUEST_READ_EXTERNAL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        if (ContextCompat.checkSelfPermission(SplashScreenActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(SplashScreenActivity.this, "Otw Permisi", Toast.LENGTH_SHORT).show();
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(SplashScreenActivity.this,
                    Manifest.permission.CAMERA)) {
                Toast.makeText(SplashScreenActivity.this, "Permisi sukses", Toast.LENGTH_SHORT).show();
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(SplashScreenActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
                String username = sp.getString(Config.SHARED_USERNAME, "");
                String email = sp.getString(Config.SHARED_EMAIL, "");
                String rule = sp.getString(Config.SHARED_RULE, "");

                // TODO jika belum masuk ke LoginActivity
                if (username.equalsIgnoreCase("") || TextUtils.isEmpty(username)){
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                // TODO jika sudah nantinya akan masuk ke Home
                else {
                    if (rule.contains("user")){
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
//                    else {
//                        startActivity(new Intent(getApplicationContext(), HomePetugasActivity.class));
//                    }

                }
            }
        }, 2000);
    }
}
