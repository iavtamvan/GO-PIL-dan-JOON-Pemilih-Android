package com.iavariav.root.gopil.Rule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iavariav.root.gopil.Helper.Config;
import com.iavariav.root.gopil.Model.HistoriOnlineModel;
import com.iavariav.root.gopil.R;
import com.iavariav.root.gopil.Rest.GAS.ApiServiceGas;
import com.iavariav.root.gopil.Rest.GAS.ClientGas;
import com.iavariav.root.gopil.Rule.Fragment.LainnyaFragment;
import com.iavariav.root.gopil.Rule.Fragment.NewsFragment;
import com.iavariav.root.gopil.Rule.Fragment.ResultVoteFragment;
import com.iavariav.root.gopil.Rule.Fragment.TpsMapsFragment;
import com.iavariav.root.gopil.Rule.Fragment.VoteFragment;
import com.iavariav.root.gopil.Service.GPSTracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private FragmentManager fragmentManager;

    private String nikSaved, voteLoginSaving;


    private ArrayList<HistoriOnlineModel> historiOnlineModels;


    GPSTracker gpsTracker;
    private double Lat, Long;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_vote:
                    SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
                    String voteSaving = sp.getString(Config.SHARED_VOTESAVING, "");
                    String voteSacingLogin = sp.getString(Config.SHARED_VOTELOGIN_SAVING, "");

                    if (voteSacingLogin.trim().contains("golput")) {
                        fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.containerViewPager, new VoteFragment()).commit();
                        getSupportActionBar().setTitle("PEMILIHAN");
//                        scheduleNotification(HomeActivity.this, 2000, 0);
                    } else {

                        fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.containerViewPager, new ResultVoteFragment()).commit();
                        getSupportActionBar().setTitle("SUARA TERHITUNG");
                    }
                    return true;
                case R.id.navigation_news:
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.containerViewPager, new NewsFragment()).commit();
                    getSupportActionBar().setTitle("BERITA");
                    return true;
                case R.id.navigation_tps:

                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.containerViewPager, new TpsMapsFragment()).commit();
                    getSupportActionBar().setTitle("LOKASI TPS");
                    return true;
                case R.id.navigation_lainnya:

                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.containerViewPager, new LainnyaFragment()).commit();
                    getSupportActionBar().setTitle("LAINNYA");
                    return true;
            }
            return false;
        }
    };
    private TextView tvHomeNama;
    private FrameLayout containerViewPager;
    private BottomNavigationView navigation;
    private String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        historiOnlineModels = new ArrayList<>();

        SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
        String voteSaving = sp.getString(Config.SHARED_VOTESAVING, "");
        String voteSacingLogin = sp.getString(Config.SHARED_VOTELOGIN_SAVING, "");
        nikSaved = sp.getString(Config.SHARED_NIK, "");
        nama = sp.getString(Config.SHARED_NAMALENGKAP, "");

        tvHomeNama.setText(nama);

        gpsTracker = new GPSTracker(this);
        if (gpsTracker.canGetLocation()) {
            Lat = gpsTracker.getLatitude();
            Long = gpsTracker.getLongitude();
            postDataLatLong();


        } else {
            gpsTracker.showSettingsAlert();
        }


//        getHistoryOnline();


        if (voteSacingLogin.trim().contains("golput")) {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.containerViewPager, new VoteFragment()).commit();
            getSupportActionBar().setTitle("PEMILIHAN");

//                        scheduleNotification(HomeActivity.this, 2000, 0);
        } else {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.containerViewPager, new ResultVoteFragment()).commit();
            getSupportActionBar().setTitle("SUARA TERHITUNG");
        }

    }

    private void postDataLatLong() {
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        apiServiceGas.updatePostRegisterLatLong("updateDataRegister", "register", nikSaved, Lat, Long)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject object = new JSONObject(response.body().string());
                                String hasil = object.optString("hasil");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        postDataLatLong();
                        Toast.makeText(HomeActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void setActionBarTitle() {
//        getSupportActionBar().setTitle(title);
        getSupportActionBar().setLogo(R.drawable.arrow);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Tutup Aplikasi")
                .setMessage("Apakah anda ingin menutup aplikasi ?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                    }

                })
                .setNegativeButton("Tidak", null)
                .show();
    }

    private void initView() {
        tvHomeNama = (TextView) findViewById(R.id.tvHomeNama);
        containerViewPager = (FrameLayout) findViewById(R.id.containerViewPager);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
    }
}
