package com.iavariav.root.joon.Rule;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iavariav.root.joon.Helper.Config;
import com.iavariav.root.joon.Model.TataCaraModel;
import com.iavariav.root.joon.R;
import com.iavariav.root.joon.Rest.GAS.ApiServiceGas;
import com.iavariav.root.joon.Rest.GAS.ClientGas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TataCaraActivity extends AppCompatActivity {

    private LinearLayout div;

    private ArrayList<TataCaraModel> tataCaraModels;

    private String no, artikel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tata_cara);
        initView();
        tataCaraModels = new ArrayList<>();

        getDataTataCara();
    }

    private void getDataTataCara() {
        ApiServiceGas apiServiceGas  = ClientGas.getInstanceRetrofit();
        Call<ArrayList<TataCaraModel>> call = apiServiceGas.getDataTataCara();
        call.enqueue(new Callback<ArrayList<TataCaraModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TataCaraModel>> call, Response<ArrayList<TataCaraModel>> response) {
                tataCaraModels = response.body();
                for (int i = 0; i < tataCaraModels.size(); i++) {
                    no = tataCaraModels.get(i).getNo();
                    artikel = tataCaraModels.get(i).getArtikel();

                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View addView = layoutInflater.inflate(R.layout.row_gopil_tatatertib, null);

                    final TextView tvRowTataTertibNomor = addView.findViewById(R.id.tvRowTataTertibNomor);
                    final TextView tvRowTataTertibArtikel = addView.findViewById(R.id.tvRowFaqJawaban);

                    tvRowTataTertibNomor.setText(no);
                    tvRowTataTertibArtikel.setText(artikel);


                    div.addView(addView);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TataCaraModel>> call, Throwable t) {
                Toast.makeText(TataCaraActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        div = (LinearLayout) findViewById(R.id.div);
    }
}
