package com.iavariav.root.joon.Rule;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iavariav.root.joon.Helper.Config;
import com.iavariav.root.joon.Model.WilayahModel;
import com.iavariav.root.joon.R;
import com.iavariav.root.joon.Rest.GAS.ApiServiceGas;
import com.iavariav.root.joon.Rest.GAS.ClientGas;
import com.iavariav.root.joon.Rule.Anggaran.AnggaranActivity;
import com.iavariav.root.joon.Rule.Anggaran.AnggaranPartaiActivity;
import com.iavariav.root.joon.Rule.Anggaran.AnggaranPribadiActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WIlayahActivity extends AppCompatActivity {

    private ArrayList<WilayahModel> wilayahModels;
    private LinearLayout div;

    private String jenisAnggaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilayah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        jenisAnggaran = bundle.getString(Config.BUNDLE_ANGGARAN_JENIS);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        jenisAnggaran = sharedPreferences.getString(Config.SHARED_ANGGARAN_JENIS, "");

        wilayahModels = new ArrayList<>();
        getDatataWilayah();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //Getting out shared preferences
        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Getting editor
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Config.SHARED_ANGGARAN_JENIS , "");
        editor.putString(Config.SHARED_ANGGARAN_DETAIL , "");
        editor.commit();

    }

    private void getDatataWilayah() {
        final ProgressDialog loading = ProgressDialog.show(WIlayahActivity.this, "Loading", "Mengambil data wilayah...", false, false);
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<WilayahModel>> call = apiServiceGas.getDataWilayah();
        call.enqueue(new Callback<ArrayList<WilayahModel>>() {
            @Override
            public void onResponse(Call<ArrayList<WilayahModel>> call, Response<ArrayList<WilayahModel>> response) {
                wilayahModels = response.body();
                for (int i = 0; i < wilayahModels.size(); i++) {
                    final String wilayah = wilayahModels.get(i).getWilayahName();

                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View addView = layoutInflater.inflate(R.layout.row_gopil_list_anggaran, null);

                    final TextView tvRowListAnggaranPilihanWilayah = addView.findViewById(R.id.tvRowListAnggaranPilihanWilayah);
                    tvRowListAnggaranPilihanWilayah.setText(wilayah);

                    final LinearLayout containerRowAnggaran = addView.findViewById(R.id.containerRowAnggaran);

                    if (jenisAnggaran.contains("Anggaran Pribadi")){
                        containerRowAnggaran.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                //Getting editor
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString(Config.SHARED_WILAYAH , wilayah);
                                editor.commit();
                                Intent intent = new Intent(getApplicationContext(), AnggaranPribadiActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                    else if (jenisAnggaran.contains("Anggaran Per Wilayah")){
                        containerRowAnggaran.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                //Getting editor
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString(Config.SHARED_WILAYAH , wilayah);
                                editor.commit();
                                Intent intent = new Intent(getApplicationContext(), AnggaranActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                    else if (jenisAnggaran.contains("Anggaran Partai")){
                        containerRowAnggaran.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                //Getting editor
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString(Config.SHARED_WILAYAH , wilayah);
                                editor.commit();
                                Intent intent = new Intent(getApplicationContext(), AnggaranPartaiActivity.class);
                                startActivity(intent);
                            }
                        });
                    }


                    div.addView(addView);
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<WilayahModel>> call, Throwable t) {
                loading.dismiss();
                getDatataWilayah();
                Toast.makeText(WIlayahActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        div = (LinearLayout) findViewById(R.id.div);
    }
}
