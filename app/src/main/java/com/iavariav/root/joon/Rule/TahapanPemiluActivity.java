package com.iavariav.root.joon.Rule;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iavariav.root.joon.Helper.Config;
import com.iavariav.root.joon.Model.TahapanPemiluModel;
import com.iavariav.root.joon.R;
import com.iavariav.root.joon.Rest.GAS.ApiServiceGas;
import com.iavariav.root.joon.Rest.GAS.ClientGas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TahapanPemiluActivity extends AppCompatActivity {

    private LinearLayout div;

    private ArrayList<TahapanPemiluModel> tahapanPemiluModels;

    private String tglAkhir;
    private String tglAwal;
    private String artikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahapan_pemilu);
        initView();

        tahapanPemiluModels = new ArrayList<>();

        getDataTahapanPemilu();
    }

    private void getDataTahapanPemilu() {
        final ProgressDialog loading = ProgressDialog.show(TahapanPemiluActivity.this, "Loading", "Mohon tunggu...", false, false);

        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<TahapanPemiluModel>> call = apiServiceGas.getDataTahapanPemilu();
        call.enqueue(new Callback<ArrayList<TahapanPemiluModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TahapanPemiluModel>> call, Response<ArrayList<TahapanPemiluModel>> response) {
                tahapanPemiluModels= response.body();
                for (int i = 0; i < tahapanPemiluModels.size(); i++) {
                    tglAkhir = tahapanPemiluModels.get(i).getTglAkhir();
                    tglAwal = tahapanPemiluModels.get(i).getTglAwal();
                    artikel = tahapanPemiluModels.get(i).getTahapanPersiapan();


                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View addView = layoutInflater.inflate(R.layout.row_gopil_tahapan_pemilu, null);

                    final TextView tvRowTahapanPemiluTanggalMulai = addView.findViewById(R.id.tvRowTahapanPemiluTanggalMulai);
                    final TextView tvRowTahapanPemiluSelesaiTanggal = addView.findViewById(R.id.tvRowTahapanPemiluSelesaiTanggal);
                    final TextView tvRowTahapanPemiluArtikel = addView.findViewById(R.id.tvRowTahapanPemiluArtikel);

                    tvRowTahapanPemiluTanggalMulai.setText(tglAwal);
                    tvRowTahapanPemiluSelesaiTanggal.setText(tglAkhir);
                    tvRowTahapanPemiluArtikel.setText(artikel);

                    div.addView(addView);

                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TahapanPemiluModel>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(TahapanPemiluActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initView() {
        div = (LinearLayout) findViewById(R.id.div);
    }
}
