package com.iavariav.root.gopil.Rule;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iavariav.root.gopil.Helper.Config;
import com.iavariav.root.gopil.R;
import com.iavariav.root.gopil.Rest.GAS.ApiServiceGas;
import com.iavariav.root.gopil.Rest.GAS.ClientGas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends Activity {

    private ImageView headerCoverImage;
    private ImageView ivProfilUser;
    private RelativeLayout profileLayout;
    private ImageView ivProfilEdit;
    private ImageView ivProfileLogout;
    private TextView tvProfilNama;
    private TextView tvProfilNIK;
    private TextView tvProfilUsername;
    private TextView tvProfilStatus;
    private EditText edtProfilTanggalLahir;
    private EditText edtProfilTempatLahir;
    private EditText edtProfilAlamat;
    private TextView tvProfilWilayahProvinsi;
    private EditText edtProfilNoHp;
    private TextView tvProfilJenisKelamin;
    private TextView tvProfilAgama;


    private String fotoUser, namaUser, username, nik, statusUser, email, tanggalLahir, tempatLahir, alamat, wilayahProvinsi, noHp, jenisKelamin, agama;
    private Button btnProfilUpdate;
    private LinearLayout divBgProfil;
    private TextView tvProfilEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        initView();

        SharedPreferences sp = ProfilActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        fotoUser = sp.getString(Config.SHARED_FOTO, "");
        namaUser = sp.getString(Config.SHARED_NAMALENGKAP, "");
        username = sp.getString(Config.SHARED_USERNAME, "");
        nik = sp.getString(Config.SHARED_NIK, "");
        statusUser = sp.getString(Config.SHARED_STATUSUSER, "");
        email = sp.getString(Config.SHARED_EMAIL, "");
        tanggalLahir = sp.getString(Config.SHARED_TGLLAHIR, "");
        tempatLahir = sp.getString(Config.SHARED_TEMPATLAHIR, "");
        alamat = sp.getString(Config.SHARED_ALAMAT, "");
        wilayahProvinsi = sp.getString(Config.SHARED_WILAYAH, "");
        noHp = sp.getString(Config.SHARED_NOHP, "");
        jenisKelamin = sp.getString(Config.SHARED_JENISKELAMIN, "");
        agama = sp.getString(Config.SHARED_AGAMA, "");


        Glide.with(this).load(fotoUser).fitCenter().error(R.drawable.logo).into(ivProfilUser);

        tvProfilNama.setText(namaUser);
        tvProfilNIK.setText(nik);
        tvProfilUsername.setText(username);
        tvProfilStatus.setText(statusUser);

        edtProfilTanggalLahir.setText(tanggalLahir);
        edtProfilTanggalLahir.setEnabled(false);
        edtProfilTanggalLahir.setFocusable(false);
        edtProfilTempatLahir.setText(tempatLahir);
        edtProfilTempatLahir.setEnabled(false);
        edtProfilTempatLahir.setFocusable(false);
        edtProfilAlamat.setText(alamat);
        edtProfilAlamat.setEnabled(false);
        edtProfilAlamat.setFocusable(false);
        edtProfilNoHp.setText(noHp);
        edtProfilNoHp.setEnabled(false);
        edtProfilNoHp.setFocusable(false);

        tvProfilWilayahProvinsi.setText(wilayahProvinsi);
        tvProfilEmail.setText(email);

        tvProfilJenisKelamin.setText(jenisKelamin);
        tvProfilAgama.setText(agama);

        ivProfilEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtProfilTanggalLahir.setEnabled(true);
                edtProfilTanggalLahir.setFocusable(true);
                edtProfilTempatLahir.setEnabled(true);
                edtProfilTempatLahir.setFocusable(true);
                edtProfilAlamat.setEnabled(true);
                edtProfilAlamat.setFocusable(true);
                edtProfilNoHp.setEnabled(true);
                edtProfilNoHp.setFocusable(true);

                btnProfilUpdate.setVisibility(View.VISIBLE);
                btnProfilUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final ProgressDialog loading = ProgressDialog.show(ProfilActivity.this, "Loading", "Valisadi data...", false, false);
                        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
                        apiServiceGas.updateProfil(
                                "updateProfil", "register",
                                tvProfilNIK.getText().toString().trim(),
                                tvProfilEmail.getText().toString().trim(),
                                edtProfilTanggalLahir.getText().toString().trim(),
                                edtProfilTempatLahir.getText().toString().trim(),
                                edtProfilAlamat.getText().toString().trim(),
                                edtProfilNoHp.getText().toString().trim()
                        ).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    loading.dismiss();
                                    Toast.makeText(ProfilActivity.this, "Sukses memperbarui profil", Toast.LENGTH_SHORT).show();
                                    edtProfilTanggalLahir.setEnabled(false);
                                    edtProfilTempatLahir.setEnabled(false);
                                    edtProfilAlamat.setEnabled(false);
                                    edtProfilNoHp.setEnabled(false);

                                    //Creating a shared preference
                                    SharedPreferences sharedPreferences = ProfilActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                    //Creating editor to store values to shared preferences
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    //Adding values to editor
                                    editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                    editor.putString(Config.SHARED_EMAIL, tvProfilEmail.getText().toString().trim());
                                    editor.putString(Config.SHARED_TGLLAHIR, edtProfilTanggalLahir.getText().toString().trim());
                                    editor.putString(Config.SHARED_TEMPATLAHIR, edtProfilTempatLahir.getText().toString().trim());
                                    editor.putString(Config.SHARED_ALAMAT, edtProfilAlamat.getText().toString().trim());
                                    editor.putString(Config.SHARED_NOHP, edtProfilNoHp.getText().toString().trim());

                                    editor.commit();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                loading.dismiss();
                                Toast.makeText(ProfilActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
                            }

                        });
                    }
                });
            }
        });

        ivProfileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Config.forceLogout(ProfilActivity.this);
                updateStatusLogin();
            }
        });
    }

    private void updateStatusLogin() {
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        apiServiceGas.updateStatusLogin("updateDataStatusLogin", "register", nik, "Tidak Aktif")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject object = new JSONObject(response.body().string());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ProfilActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        ivProfilUser = (ImageView) findViewById(R.id.ivProfilUser);
        ivProfilEdit = (ImageView) findViewById(R.id.ivProfilEdit);
        ivProfileLogout = (ImageView) findViewById(R.id.ivProfileLogout);
        tvProfilNama = (TextView) findViewById(R.id.tvProfilNama);
        tvProfilNIK = (TextView) findViewById(R.id.tvProfilNIK);
        tvProfilUsername = (TextView) findViewById(R.id.tvProfilUsername);
        tvProfilStatus = (TextView) findViewById(R.id.tvProfilStatus);
        edtProfilTanggalLahir = (EditText) findViewById(R.id.edtProfilTanggalLahir);
        edtProfilTempatLahir = (EditText) findViewById(R.id.edtProfilTempatLahir);
        edtProfilAlamat = (EditText) findViewById(R.id.edtProfilAlamat);
        tvProfilWilayahProvinsi = (TextView) findViewById(R.id.tvProfilWilayahProvinsi);
        edtProfilNoHp = (EditText) findViewById(R.id.edtProfilNoHp);
        tvProfilJenisKelamin = (TextView) findViewById(R.id.tvProfilJenisKelamin);
        tvProfilAgama = (TextView) findViewById(R.id.tvProfilAgama);
        btnProfilUpdate = (Button) findViewById(R.id.btnProfilUpdate);
        divBgProfil = (LinearLayout) findViewById(R.id.divBgProfil);
        tvProfilEmail = (TextView) findViewById(R.id.tvProfilEmail);
    }
}
