package com.iavariav.root.gopil.Rule;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iavariav.root.gopil.Helper.Config;
import com.iavariav.root.gopil.Model.FaqModel;
import com.iavariav.root.gopil.R;
import com.iavariav.root.gopil.Rest.GAS.ApiServiceGas;
import com.iavariav.root.gopil.Rest.GAS.ClientGas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAQActivity extends AppCompatActivity {

    private LinearLayout div;

    private ArrayList<FaqModel> faqModels;

    private String tgl, pertanyaan, jawaban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        initView();
        faqModels = new ArrayList<>();

        getDataFaq();
    }

    private void getDataFaq() {
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<FaqModel>> call = apiServiceGas.getDataFaq();
        call.enqueue(new Callback<ArrayList<FaqModel>>() {
            @Override
            public void onResponse(Call<ArrayList<FaqModel>> call, Response<ArrayList<FaqModel>> response) {
                faqModels = response.body();
                for (int i = 0; i < faqModels.size(); i++) {
                    tgl = faqModels.get(i).getWaktu();
                    pertanyaan = faqModels.get(i).getPertanyaan();
                    jawaban = faqModels.get(i).getJawaban();

                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View addView = layoutInflater.inflate(R.layout.row_gopil_faq, null);


                    final TextView tvRowFaqTanggal = addView.findViewById(R.id.tvRowFaqTanggal);
                    tvRowFaqTanggal.setText(tgl);

                    final TextView tvRowFaqPertanyaan = addView.findViewById(R.id.tvRowFaqPertanyaan);
                    tvRowFaqPertanyaan.setText(pertanyaan);

                    final TextView tvRowFaqJawaban = addView.findViewById(R.id.tvRowFaqJawaban);
                    tvRowFaqJawaban.setText(jawaban);

                    div.addView(addView);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<FaqModel>> call, Throwable t) {
                Toast.makeText(FAQActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        div = (LinearLayout) findViewById(R.id.div);
    }
}
