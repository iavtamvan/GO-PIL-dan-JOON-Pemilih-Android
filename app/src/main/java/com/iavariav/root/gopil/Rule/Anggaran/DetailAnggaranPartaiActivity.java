package com.iavariav.root.gopil.Rule.Anggaran;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iavariav.root.gopil.Helper.Config;
import com.iavariav.root.gopil.Model.AnggaranPartaiModel;
import com.iavariav.root.gopil.R;
import com.iavariav.root.gopil.Rest.GAS.ApiServiceGas;
import com.iavariav.root.gopil.Rest.GAS.ClientGas;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAnggaranPartaiActivity extends AppCompatActivity {

    String namaPartai, fotoPartai;
    private LinearLayout divContainerDetailAnggaranPartai;
    private ImageView ivDetailAnggaranPartai;
    private TextView tvDetailAnggaranPartaiNamaPartai;
    private TextView tvDetailAnggaranPartaiPeriode1;
    private TextView tvDetailAnggaranPartaiPeriode2;
    private TextView tvDetailAnggaranPartaiPeriode3;
    private TextView tvDetailAnggaranPartaiPeriode4;
    private TextView tvDetailAnggaranPartaiPeriode5;

    private ArrayList<AnggaranPartaiModel> anggaranPartaiModels;
    private TextView tvDetailAnggaranPartaiTotal;

    private Locale localeID;
    private NumberFormat formatRupiah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anggaran_partai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();

        anggaranPartaiModels = new ArrayList<>();

        localeID = new Locale("in", "ID");
        formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        namaPartai = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_NAMA);
        fotoPartai = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_FOTO_PARTAI);
        tvDetailAnggaranPartaiNamaPartai.setText(namaPartai);
        Glide.with(getApplicationContext()).load(fotoPartai).crossFade().error(R.drawable.logo).into(ivDetailAnggaranPartai);

        getDataAnggaranPartai();
    }

    private void getDataAnggaranPartai() {
        final ProgressDialog loading = ProgressDialog.show(DetailAnggaranPartaiActivity.this, "Loading", "Mengambil data partai...", false, false);
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<AnggaranPartaiModel>> call = apiServiceGas.getDataAnggaranPartai();
        call.enqueue(new Callback<ArrayList<AnggaranPartaiModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AnggaranPartaiModel>> call, Response<ArrayList<AnggaranPartaiModel>> response) {
                anggaranPartaiModels = response.body();
                for (AnggaranPartaiModel s : anggaranPartaiModels) {
                    if (s.getNamaPartai() != null && s.getNamaPartai().contains(namaPartai)) {
                        tvDetailAnggaranPartaiPeriode1.setText(s.getPeriode1());
                        tvDetailAnggaranPartaiPeriode2.setText(s.getPeriode2());
                        tvDetailAnggaranPartaiPeriode3.setText(s.getPeriode3());
                        tvDetailAnggaranPartaiPeriode4.setText(s.getPeriode4());
                        tvDetailAnggaranPartaiPeriode5.setText(s.getPeriode5());
                        tvDetailAnggaranPartaiTotal.setText(s.getTotal());

                        loading.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AnggaranPartaiModel>> call, Throwable t) {
                loading.dismiss();
                getDataAnggaranPartai();
                Toast.makeText(DetailAnggaranPartaiActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        divContainerDetailAnggaranPartai = (LinearLayout) findViewById(R.id.divContainerDetailAnggaranPartai);
        ivDetailAnggaranPartai = (ImageView) findViewById(R.id.ivDetailAnggaranPartai);
        tvDetailAnggaranPartaiNamaPartai = (TextView) findViewById(R.id.tvDetailAnggaranPartaiNamaPartai);
        tvDetailAnggaranPartaiPeriode1 = (TextView) findViewById(R.id.tvDetailAnggaranPartaiPeriode1);
        tvDetailAnggaranPartaiPeriode2 = (TextView) findViewById(R.id.tvDetailAnggaranPartaiPeriode2);
        tvDetailAnggaranPartaiPeriode3 = (TextView) findViewById(R.id.tvDetailAnggaranPartaiPeriode3);
        tvDetailAnggaranPartaiPeriode4 = (TextView) findViewById(R.id.tvDetailAnggaranPartaiPeriode4);
        tvDetailAnggaranPartaiPeriode5 = (TextView) findViewById(R.id.tvDetailAnggaranPartaiPeriode5);
        tvDetailAnggaranPartaiTotal = (TextView) findViewById(R.id.tvDetailAnggaranPartaiTotal);
    }
}
