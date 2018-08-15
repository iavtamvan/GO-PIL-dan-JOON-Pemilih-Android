package com.iavariav.root.gopil.Rule;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iavariav.root.gopil.Helper.Config;
import com.iavariav.root.gopil.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class LaporanAkhirPemilihActivity extends AppCompatActivity {

    private TextView tvLaporanAkhirJudulToolbar;
    private ImageView ivLaporanAkhirCaptureScreen;
    private CircleImageView ciLaporanAkhir;
    private TextView tvLaporanAkhirNik;
    private TextView tvLaporanAkhirToken;
    private TextView tvLaporanAkhirID;
    private TextView tvLaporanAkhirNamaLengkap;
    private TextView tvLaporanAkhirJenisKelamin;
    private TextView tvLaporanAkhirWilayah;
    private TextView tvLaporanAkhirStatusAplikasi;
    private TextView tvLaporanAkhirStatusHakSuara;
    private ImageView ivLaporanAkhirDigitalSignature;
    private CircleImageView ciLaporanAkhirFotoFront;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_akhir_pemilih);
        getSupportActionBar().hide();
        initView();

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Glide.with(LaporanAkhirPemilihActivity.this).load(sharedPreferences.getString(Config.SHARED_FOTO, "")).into(ciLaporanAkhir);
        Glide.with(LaporanAkhirPemilihActivity.this).load(sharedPreferences.getString(Config.SHARED_DIGITAL_SIGNATURE, "")).into(ivLaporanAkhirDigitalSignature);
        Glide.with(LaporanAkhirPemilihActivity.this).load(sharedPreferences.getString(Config.SHARED_FOTO_FRONT, "")).into(ciLaporanAkhirFotoFront);

        tvLaporanAkhirNik.setText(sharedPreferences.getString(Config.SHARED_NIK, ""));
        tvLaporanAkhirToken.setText(sharedPreferences.getString(Config.SHARED_TOKEN, ""));
        tvLaporanAkhirID.setText(sharedPreferences.getString(Config.SHARED_HWID, ""));
        tvLaporanAkhirNamaLengkap.setText(sharedPreferences.getString(Config.SHARED_NAMALENGKAP, ""));
        tvLaporanAkhirJenisKelamin.setText(sharedPreferences.getString(Config.SHARED_JENISKELAMIN, ""));
        tvLaporanAkhirWilayah.setText(sharedPreferences.getString(Config.SHARED_WILAYAH, ""));
        tvLaporanAkhirStatusAplikasi.setText(sharedPreferences.getString(Config.SHARED_STATUS_APLIKASI, ""));
        tvLaporanAkhirStatusHakSuara.setText(sharedPreferences.getString(Config.SHARED_STATUS_VOTE, ""));

        ivLaporanAkhirCaptureScreen.setVisibility(View.GONE);
        ivLaporanAkhirCaptureScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeScreenshot();
            }
        });
    }

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
            Toast.makeText(this, "" + mPath, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "" + mPath, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "" + mPath, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "" + mPath, Toast.LENGTH_SHORT).show();
            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    private void initView() {
        tvLaporanAkhirJudulToolbar = (TextView) findViewById(R.id.tvLaporanAkhirJudulToolbar);
        ivLaporanAkhirCaptureScreen = (ImageView) findViewById(R.id.ivLaporanAkhirCaptureScreen);
        ciLaporanAkhir = (CircleImageView) findViewById(R.id.ciLaporanAkhir);
        tvLaporanAkhirNik = (TextView) findViewById(R.id.tvLaporanAkhirNik);
        tvLaporanAkhirToken = (TextView) findViewById(R.id.tvLaporanAkhirToken);
        tvLaporanAkhirID = (TextView) findViewById(R.id.tvLaporanAkhirID);
        tvLaporanAkhirNamaLengkap = (TextView) findViewById(R.id.tvLaporanAkhirNamaLengkap);
        tvLaporanAkhirJenisKelamin = (TextView) findViewById(R.id.tvLaporanAkhirJenisKelamin);
        tvLaporanAkhirWilayah = (TextView) findViewById(R.id.tvLaporanAkhirWilayah);
        tvLaporanAkhirStatusAplikasi = (TextView) findViewById(R.id.tvLaporanAkhirStatusAplikasi);
        tvLaporanAkhirStatusHakSuara = (TextView) findViewById(R.id.tvLaporanAkhirStatusHakSuara);
        ivLaporanAkhirDigitalSignature = (ImageView) findViewById(R.id.ivLaporanAkhirDigitalSignature);
        ciLaporanAkhirFotoFront = (CircleImageView) findViewById(R.id.ciLaporanAkhirFotoFront);
    }
}
