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
import com.iavariav.root.joon.Model.AnggaranPribadiModel;
import com.iavariav.root.joon.R;
import com.iavariav.root.joon.Rest.GAS.ApiServiceGas;
import com.iavariav.root.joon.Rest.GAS.ClientGas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAnggaranPribadiActivity extends AppCompatActivity {

    private TextView tvDialogBoxAnggaranPribadiJudul;
    private LinearLayout div;

    private String namaCalon;
    private ArrayList<AnggaranPribadiModel> anggaranPribadiModels ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anggaran_pribadi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        namaCalon = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_NAMA_CALON);
        Toast.makeText(this, "" + namaCalon, Toast.LENGTH_SHORT).show();

        tvDialogBoxAnggaranPribadiJudul.setText(namaCalon);
        anggaranPribadiModels = new ArrayList<>();
        getDataAnggaranPribadi();
    }

    private void getDataAnggaranPribadi() {
        final ProgressDialog loading = ProgressDialog.show(DetailAnggaranPribadiActivity.this, "Loading", "Mengambil data partai...", false, false);
        ApiServiceGas apiServiceGas  = ClientGas.getInstanceRetrofit();
        Call<ArrayList<AnggaranPribadiModel>> call = apiServiceGas.getDataAnggaranPribadi();
        call.enqueue(new Callback<ArrayList<AnggaranPribadiModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AnggaranPribadiModel>> call, Response<ArrayList<AnggaranPribadiModel>> response) {
                anggaranPribadiModels = response.body();
                for (AnggaranPribadiModel s : anggaranPribadiModels){
                    if (s.getNamaCalon() != null && s.getNamaCalon().contains(namaCalon)){

                        String jenisHarta = s.getJenisHarta();
                        String keterangan = s.getKeterangan();
                        String nilai = s.getNilai();
                        String foto = s.getFoto();
                        String namaCalon = s.getNamaCalon();
                        String wilayah = s.getWilayah();
                        String jenisAnggaran = s.getJenisAnggaran();

                        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View addView = layoutInflater.inflate(R.layout.row_gopil_anggaran_pribadi, null);

                        final TextView tvRowAnggaranPribadiJenisHarta = addView.findViewById(R.id.tvRowAnggaranPribadiJenisHarta);
                        tvRowAnggaranPribadiJenisHarta.setText(jenisHarta);

                        final TextView tvRowAnggaranPribadiKeterangan = addView.findViewById(R.id.tvRowAnggaranPribadiKeterangan);
                        tvRowAnggaranPribadiKeterangan.setText(keterangan);

                        final TextView tvRowAnggaranPribadiNilai = addView.findViewById(R.id.tvRowAnggaranPribadiNilai);
                        tvRowAnggaranPribadiNilai.setText(nilai);

                        div.addView(addView);

                        loading.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AnggaranPribadiModel>> call, Throwable t) {
                loading.dismiss();
                getDataAnggaranPribadi();
                Toast.makeText(DetailAnggaranPribadiActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        tvDialogBoxAnggaranPribadiJudul = (TextView) findViewById(R.id.tvDialogBoxAnggaranPribadiJudul);
        div = (LinearLayout) findViewById(R.id.div);
    }
}
