package com.iavariav.root.joon.Rule.Anggaran;

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
import com.iavariav.root.joon.Model.DaftarAnggaranModel;
import com.iavariav.root.joon.R;
import com.iavariav.root.joon.Rest.GAS.ApiServiceGas;
import com.iavariav.root.joon.Rest.GAS.ClientGas;
import com.iavariav.root.joon.Rule.WIlayahActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarAnggaranActivity extends AppCompatActivity {

    private LinearLayout div;
    private ArrayList<DaftarAnggaranModel> daftarAnggaranModels ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_anggaran);
        initView();

        daftarAnggaranModels = new ArrayList<>();
        getDaftarAngnaran();



    }

    private void getDaftarAngnaran() {
        final ProgressDialog loading = ProgressDialog.show(DaftarAnggaranActivity.this, "Loading", "Mengambil data anggaran...", false, false);
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<DaftarAnggaranModel>> call = apiServiceGas.getDataDaftarAnggaran();
        call.enqueue(new Callback<ArrayList<DaftarAnggaranModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DaftarAnggaranModel>> call, Response<ArrayList<DaftarAnggaranModel>> response) {
                daftarAnggaranModels = response.body();
                for (int i = 0; i < daftarAnggaranModels.size(); i++) {
                    final String jenisAnggaran = daftarAnggaranModels.get(i).getJenisAnggaran();
                    final String detail = daftarAnggaranModels.get(i).getDetail();

                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View addView = layoutInflater.inflate(R.layout.row_gopil_list_daftar_anggaran, null);

                    final TextView tvRowListAnggaranPilihanJenis = addView.findViewById(R.id.tvRowListAnggaranPilihanJenis);
                    tvRowListAnggaranPilihanJenis.setText(jenisAnggaran);

                    final TextView tvDaftarAnggaranSub = addView.findViewById(R.id.tvDaftarAnggaranSub);
                    tvDaftarAnggaranSub.setText(detail);

                    final LinearLayout containerRowAnggaran = addView.findViewById(R.id.containerRowAnggaran);

                        containerRowAnggaran.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (jenisAnggaran.contains("Anggaran KPU (COOMING SOON)")){
                                    Toast.makeText(DaftarAnggaranActivity.this, "Cooming Soon", Toast.LENGTH_SHORT).show();
                                } else {
                                    SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.putString(Config.SHARED_ANGGARAN_JENIS, jenisAnggaran);
                                    editor.putString(Config.SHARED_ANGGARAN_DETAIL, detail);
                                    editor.commit();


                                    startActivity(new Intent(getApplicationContext(), WIlayahActivity.class));
                                }

//                            Bundle bundle = new Bundle();
//                            bundle.putString(Config.BUNDLE_ANGGARAN_JENIS, jenisAnggaran);
//                            bundle.putString(Config.BUNDLE_ANGGARAN_DETAIL, detail);
//                            Intent intent = new Intent(getApplicationContext(), WIlayahActivity.class);
//                            intent.putExtras(bundle);
//                            startActivity(intent);
                            }
                        });

                        loading.dismiss();
                    div.addView(addView);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DaftarAnggaranModel>> call, Throwable t) {
                loading.dismiss();
                getDaftarAngnaran();
                Toast.makeText(DaftarAnggaranActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        div = (LinearLayout) findViewById(R.id.div);
    }
}
