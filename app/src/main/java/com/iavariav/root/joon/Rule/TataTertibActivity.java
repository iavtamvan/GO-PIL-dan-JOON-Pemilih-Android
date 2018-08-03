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
import com.iavariav.root.joon.Model.TataTertibModel;
import com.iavariav.root.joon.R;
import com.iavariav.root.joon.Rest.GAS.ApiServiceGas;
import com.iavariav.root.joon.Rest.GAS.ClientGas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TataTertibActivity extends AppCompatActivity {

    private LinearLayout div;

    private ArrayList<TataTertibModel> tataTertibList;

    private String no;
    private String artikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tata_tertib);
        initView();

        tataTertibList = new ArrayList<>();

        getDataTataTertib();
    }

    private void getDataTataTertib() {
        final ProgressDialog loading = ProgressDialog.show(TataTertibActivity.this, "Loading", "Mohon tunggu...", false, false);

        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<TataTertibModel>> call = apiServiceGas.getDataTataTertib();
        call.enqueue(new Callback<ArrayList<TataTertibModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TataTertibModel>> call, Response<ArrayList<TataTertibModel>> response) {
                tataTertibList = response.body();
                for (int i = 0; i < tataTertibList.size(); i++) {
                    no = tataTertibList.get(i).getNo();
                    artikel = tataTertibList.get(i).getArtikel();

                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View addView = layoutInflater.inflate(R.layout.row_gopil_tatatertib, null);

                    final TextView tvRowTataTertibNomor = addView.findViewById(R.id.tvRowTataTertibNomor);
                    final TextView tvRowTataTertibArtikel = addView.findViewById(R.id.tvRowFaqJawaban);

                    tvRowTataTertibNomor.setText(no);
                    tvRowTataTertibArtikel.setText(artikel);


                    div.addView(addView);
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TataTertibModel>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(TataTertibActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        div = (LinearLayout) findViewById(R.id.div);
    }
}
