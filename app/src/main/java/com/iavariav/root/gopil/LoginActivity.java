package com.iavariav.root.gopil;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.iavariav.root.gopil.Helper.Config;
import com.iavariav.root.gopil.Model.HistoriOnlineModel;
import com.iavariav.root.gopil.Model.LoginModel;
import com.iavariav.root.gopil.Rest.GAS.ApiServiceGas;
import com.iavariav.root.gopil.Rest.GAS.ClientGas;
import com.iavariav.root.gopil.Rule.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL = 1;
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private ArrayList<LoginModel> loginModels;
    private ArrayList<HistoriOnlineModel> historiOnlineModels;
    private String nikSaved;
    private String namaLengkap;
    private String agama;
    private String rule;
    private String tglLahir;
    private String alamat;
    private String nik;
    private String password;
    private String tempatLahir;
    private String foto;
    private String waktu;
    private String nohp;
    private String jenisKelamin;
    private String email;
    private String username;
    private String token;
    private String wilayah;
    private String status;
    private String namaPetugas;
    private String voteLoginSaving;
    private String idhp;
    private String statusVoted;
    private String digitalSignature;
    private String statusAplikasi;
    private String fotoMuka;
    private String statusLogin;

    private SliderLayout mSliderSlider;
    private PagerIndicator customIndicator;
    private PagerIndicator customIndicator2;
    private LinearLayout divHubungiKami;
    private AlertDialog.Builder builder;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initView();

        loginModels = new ArrayList<>();
        historiOnlineModels = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Otw Permisi", Toast.LENGTH_SHORT).show();
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "Permisi sukses", Toast.LENGTH_SHORT).show();
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


//        HashMap<String, String> url_maps = new HashMap<String, String>();
        // * Get internet
//        url_maps.put(sliderModels.get(i).getNama(), sliderModels.get(i).getLink());
        // * Get internet

//        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
//        file_maps.put("", R.drawable.istana);
//        file_maps.put("", R.drawable.istana);
//        file_maps.put("", R.drawable.perkotaan);
////        file_maps.put("", R.drawable.koranberita);
////        file_maps.put("", R.drawable.bangunan);
//
//        for (String name : file_maps.keySet()) {
//            TextSliderView textSliderView = new TextSliderView(LoginActivity.this);
//            // initialize a SliderLayout
//            textSliderView
//                    .image(file_maps.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.Fit);
//
//            mSliderSlider.addSlider(textSliderView);
//        }
//        mSliderSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
//        mSliderSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        mSliderSlider.setCustomAnimation(new DescriptionAnimation());
//        mSliderSlider.setDuration(4000);
//
//
//        SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUsername.getText().toString().trim().isEmpty() || edtPassword.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "" + Config.ERROR_FORM_LOGIN_KURANG, Toast.LENGTH_SHORT).show();
                } else {
                    getDataLogins();


                }
            }
        });

        divHubungiKami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder = new AlertDialog.Builder(LoginActivity.this);
                inflater = LayoutInflater.from(LoginActivity.this);
                final View dialView = inflater.inflate(R.layout.dialog_box_hubungi_kami, null);
                builder.setView(dialView);
                builder.show();
            }
        });

//        getHtoryOnline();

    }

    private void updateStatusLogin() {
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        apiServiceGas.updateStatusLogin("updateDataStatusLogin", "register", nik, "Aktif")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject object = new JSONObject(response.body().string());
                                statusLogin = object.getString("hasil");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getDataLogins() {

        final ProgressDialog loading = ProgressDialog.show(LoginActivity.this, "Loading", "Validasi data...", false, false);
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<LoginModel>> call = apiServiceGas.getLogin();
        call.enqueue(new Callback<ArrayList<LoginModel>>() {
            @Override
            public void onResponse(Call<ArrayList<LoginModel>> call, Response<ArrayList<LoginModel>> response) {
                loginModels = response.body();
                for (final LoginModel s : loginModels) {
                    if (s.getUsername() != null && s.getUsername().contains(edtUsername.getText().toString().trim())) {
                        namaLengkap = s.getNamaLengkap();
                        agama = s.getAgama();
                        rule = s.getRule();
                        tglLahir = s.getTglLahir();
                        alamat = s.getAlamat();
                        nik = s.getNik();
                        password = s.getPassword();
                        tempatLahir = s.getTempatLahir();
                        foto = s.getFoto();
                        waktu = s.getWaktu();
                        nohp = s.getNohp();
                        jenisKelamin = s.getJenisKelamin();
                        email = s.getEmail();
                        username = s.getUsername();
                        token = s.getToken();
                        wilayah = s.getWilayah();
                        status = s.getStatus();
                        namaPetugas = s.getPetugas();
                        fotoMuka = s.getFoto_muka();


                        // metode penting

                        idhp = s.getIdhp();
                        statusVoted = s.getStatus_voted();
                        digitalSignature = s.getDigital_signature();
                        statusAplikasi = s.getStatus_aplikasi();
                        statusLogin = s.getStatus_login();

                        if (idhp.isEmpty() && statusVoted.isEmpty() && digitalSignature.isEmpty() && statusAplikasi.isEmpty()
                                && fotoMuka.isEmpty()) {
                            idhp = statusVoted = digitalSignature = statusAplikasi = fotoMuka = "null";
                        }
                        getHistori();

                        loading.dismiss();

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<LoginModel>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(LoginActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getHistori() {
        final ProgressDialog loading = ProgressDialog.show(LoginActivity.this, "Loading", "Mohon tunggu...", false, false);
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<HistoriOnlineModel>> call = apiServiceGas.getDataHistoriOnline();
        call.enqueue(new Callback<ArrayList<HistoriOnlineModel>>() {
            @Override
            public void onResponse(Call<ArrayList<HistoriOnlineModel>> call, Response<ArrayList<HistoriOnlineModel>> response) {
                historiOnlineModels = response.body();
                for (final HistoriOnlineModel s : historiOnlineModels) {
                    if (s.getNik() != null && s.getNik().contains(nik)) {
//                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        if (edtUsername.getText().toString().trim().contains(username) && edtPassword.getText().toString().trim().contains(password)) {
                            if (rule.contains("user")) {

                                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                editor.putString(Config.SHARED_NAMALENGKAP, namaLengkap);
                                editor.putString(Config.SHARED_AGAMA, agama);
                                editor.putString(Config.SHARED_RULE, rule);
                                editor.putString(Config.SHARED_TGLLAHIR, tglLahir);
                                editor.putString(Config.SHARED_ALAMAT, alamat);
                                editor.putString(Config.SHARED_NIK, nik);
                                editor.putString(Config.SHARED_PASSWORD, password);
                                editor.putString(Config.SHARED_TEMPATLAHIR, tempatLahir);
                                editor.putString(Config.SHARED_FOTO, foto);
                                editor.putString(Config.SHARED_WAKTU, waktu);
                                editor.putString(Config.SHARED_NOHP, nohp);
                                editor.putString(Config.SHARED_JENISKELAMIN, jenisKelamin);
                                editor.putString(Config.SHARED_EMAIL, email);
                                editor.putString(Config.SHARED_USERNAME, username);
                                editor.putString(Config.SHARED_WILAYAH, wilayah);
                                editor.putString(Config.SHARED_STATUSUSER, status);
                                editor.putString(Config.SHARED_TOKEN, token);
                                editor.putString(Config.SHARED_VOTELOGIN_SAVING, s.getStatusVote());
                                editor.putString(Config.SHARED_PETUGAS, namaPetugas);
                                editor.putString(Config.SHARED_HWID, idhp);
                                editor.putString(Config.SHARED_STATUS_APLIKASI, statusAplikasi);
                                editor.putString(Config.SHARED_STATUS_VOTE, statusVoted);
                                editor.putString(Config.SHARED_DIGITAL_SIGNATURE, digitalSignature);
                                editor.putString(Config.SHARED_FOTO_FRONT, fotoMuka);
                                editor.commit();
                                if (statusAplikasi.toString().trim().equalsIgnoreCase("Aktif")
                                        && statusLogin.toString().trim().equalsIgnoreCase("Tidak Aktif")) {
                                    loading.dismiss();
                                    updateStatusLogin();
                                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                } else {
                                    loading.dismiss();
                                    Toast.makeText(LoginActivity.this, "" + Config.ERROR_ERROR_APPS_NOT_ACTIVED, Toast.LENGTH_SHORT).show();
                                }


                            }

                        } else {
                            loading.dismiss();
                            Toast.makeText(LoginActivity.this, "" + Config.ERROR_FORM_LOGIN, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<HistoriOnlineModel>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(LoginActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        mSliderSlider = (SliderLayout) findViewById(R.id.mSliderSlider);
        customIndicator = (PagerIndicator) findViewById(R.id.custom_indicator);
        customIndicator2 = (PagerIndicator) findViewById(R.id.custom_indicator2);
        divHubungiKami = (LinearLayout) findViewById(R.id.divHubungiKami);
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

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
