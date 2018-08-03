package com.iavariav.root.joon.Rule.Anggaran;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iavariav.root.joon.Helper.Config;
import com.iavariav.root.joon.Model.AnggaranModel;
import com.iavariav.root.joon.R;
import com.iavariav.root.joon.Rest.GAS.ApiServiceGas;
import com.iavariav.root.joon.Rest.GAS.ClientGas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnggaranActivity extends AppCompatActivity {

    private LinearLayout div;
    private ArrayList<AnggaranModel> anggaranModels;

    private String namaSatker;
    private String usulanAnggaran;
    private String usulanAnggaranDisetujui;
    private String termin1;
    private String termin2;
    private String termin3;
    private String termin11;
    private String termin22;
    private String termin33;
    private String total;
    private String nomorNhpd;
    private String tanggalNphd;
    private String wilayah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggaran);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        wilayah = bundle.getString(Config.BUNDLE_DATA_WILAYAH);

        anggaranModels = new ArrayList<>();

        getDataAnggaranWilayah();
        
    }

    private void getDataAnggaranWilayah() {
        final ProgressDialog loading = ProgressDialog.show(AnggaranActivity.this, "Loading", "Mohon tunggu...", false, false);

        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<AnggaranModel>> call = apiServiceGas.getDataAnggaran();
        call.enqueue(new Callback<ArrayList<AnggaranModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AnggaranModel>> call, Response<ArrayList<AnggaranModel>> response) {
                anggaranModels = response.body();
                
                for (AnggaranModel s : anggaranModels){
                    if (s.getWilayah() != null && s.getWilayah().contains(wilayah)){
                        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View addView = layoutInflater.inflate(R.layout.row_gopil_list_anggaran, null);

                        namaSatker = s.getNamaSatker();
                        usulanAnggaran = s.getUsulanAnggaranNPHDPemilihanSerentakTahun2018();
                        usulanAnggaranDisetujui = s.getAnggaranYangDisetujui();
                        termin1 = s.getTerminI();
                        termin2 = s.getTerminII();
                        termin3 = s.getTerminIII();
                        termin11 = s.getTerminSatu();
                        termin22 = s.getTerminDua();
                        termin33 = s.getTermStringiga();
                        total = s.getTotal();
                        nomorNhpd = s.getNomorNHPD();
                        tanggalNphd = s.getTanggalNPHD();

                        final TextView tvRowListAnggaranPilihanWilayah = addView.findViewById(R.id.tvRowListAnggaranPilihanWilayah);
                        final LinearLayout containerRowAnggaran = addView.findViewById(R.id.containerRowAnggaran);

                        tvRowListAnggaranPilihanWilayah.setText(s.getNamaSatker());
                        containerRowAnggaran.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle bundle = new Bundle();
                                bundle.putString(Config.BUNDLE_ANGGARAN_DETAIL_NAMASATKER, namaSatker);
                                bundle.putString(Config.BUNDLE_ANGGARAN_DETAIL_USULANANGGARAN, usulanAnggaran);
                                bundle.putString(Config.BUNDLE_ANGGARAN_DETAIL_USULANANGGARANDISETUJUI, usulanAnggaranDisetujui);
                                bundle.putString(Config.BUNDLE_ANGGARAN_DETAIL_TERMIN1, termin1);
                                bundle.putString(Config.BUNDLE_ANGGARAN_DETAIL_TERMIN2, termin2);
                                bundle.putString(Config.BUNDLE_ANGGARAN_DETAIL_TERMIN3, termin3);
                                bundle.putString(Config.BUNDLE_ANGGARAN_DETAIL_TERMIN11, termin11);
                                bundle.putString(Config.BUNDLE_ANGGARAN_DETAIL_TERMIN22, termin22);
                                bundle.putString(Config.BUNDLE_ANGGARAN_DETAIL_TERMIN33, termin33);
                                bundle.putString(Config.BUNDLE_ANGGARAN_DETAIL_TOTAL, total);
                                bundle.putString(Config.BUNDLE_ANGGARAN_DETAIL_NOMORNHPD, nomorNhpd);
                                bundle.putString(Config.BUNDLE_ANGGARAN_DETAIL_TANGGALNPHD, tanggalNphd);

                                Intent intent = new Intent(getApplicationContext(), DetailAnggaranActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                        
                        div.addView(addView);

                        loading.dismiss();

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AnggaranModel>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(AnggaranActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        div = (LinearLayout) findViewById(R.id.div);
    }
}
