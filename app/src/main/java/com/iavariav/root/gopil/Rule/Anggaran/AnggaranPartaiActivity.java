package com.iavariav.root.gopil.Rule.Anggaran;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iavariav.root.gopil.Helper.Config;
import com.iavariav.root.gopil.R;
import com.iavariav.root.gopil.Rest.GAS.ApiServiceGas;
import com.iavariav.root.gopil.Rest.GAS.ClientGas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnggaranPartaiActivity extends AppCompatActivity {

    private LinearLayout div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggaran_partai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();

        getData();
    }


    private void getData() {
        final ProgressDialog loading = ProgressDialog.show(AnggaranPartaiActivity.this, "Loading", "Mengambil data partai...", false, false);
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ResponseBody> call = apiServiceGas.getDataPartai();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.optJSONObject(i);
                            final String singkatan = object.optString("singkatan");
                            final String alamat = object.optString("alamat");
                            final String date = object.optString("date");
                            final String notaris_no = object.optString("notaris_no");
                            final String notaris_nama = object.optString("notaris_nama");
                            final String notaris_tanggal_pendirian = object.optString("notaris_tanggal_pendirian");
                            final String kemkumham_no = object.optString("kemkumham_no");
                            final String kemkumham_tanggal = object.optString("kemkumham_tanggal");
                            final String notelp = object.optString("notelp");
                            final String fax = object.optString("fax");
                            final String ketum = object.optString("ketum");
                            final String bendum = object.optString("bendum");
                            final String sekjen = object.optString("sekjen");
                            final String bank_no = object.optString("bank_no");
                            final String bank_nama = object.optString("bank_nama");
                            final String bank_pemilik = object.optString("bank_pemilik");
                            final String tanggal_cd = object.optString("tanggal_cd");
                            final String tanggal_hardcopy = object.optString("tanggal_hardcopy");
                            final String foto_partai = object.optString("foto_partai");
                            final String nama = object.optString("nama");
                            final String visi = object.optString("visi");
                            final String misi = object.optString("misi");

                            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View addView = layoutInflater.inflate(R.layout.row_gopil_partai, null);

                            final TextView tvRowGoPilPartaiSekjen = (TextView) addView.findViewById(R.id.tvRowGoPilPartaiSekjen);
                            final TextView tvRowGoPilPartaiBendum = (TextView) addView.findViewById(R.id.tvRowGoPilPartaiBendum);
                            final TextView tvRowGoPilPartaiKetum = (TextView) addView.findViewById(R.id.tvRowGoPilPartaiKetum);
                            final TextView tvRowGoPilPartaiDate = (TextView) addView.findViewById(R.id.tvRowGoPilPartaiDate);
                            final TextView tvRowGoPilPartaiSingkatan = (TextView) addView.findViewById(R.id.tvRowGoPilPartaiSingkatan);
                            final TextView tvRowGoPilPartaiNama = (TextView) addView.findViewById(R.id.tvRowGoPilPartaiNama);
                            final ImageView ivRowGoPilPartai = (ImageView) addView.findViewById(R.id.ivRowPartai);
                            final LinearLayout containerPartai = addView.findViewById(R.id.containerPartai);
                            Glide.with(getApplicationContext()).load(foto_partai).error(R.drawable.logo).crossFade().into(ivRowGoPilPartai);
                            tvRowGoPilPartaiSekjen.setText(sekjen);
                            tvRowGoPilPartaiBendum.setText(bendum);
                            tvRowGoPilPartaiKetum.setText(ketum);
                            tvRowGoPilPartaiDate.setText(date);
                            tvRowGoPilPartaiSingkatan.setText("(" + singkatan + ")");
                            tvRowGoPilPartaiNama.setText(nama);

                            containerPartai.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_SINGKATAN, singkatan);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_ALAMAT, alamat);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_DATE, date);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_NOTARIS_NO, notaris_no);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_NOTARIS_NAMA, notaris_nama);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_NOTARIS_TANGGAL_PENDIRIAN, notaris_tanggal_pendirian);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_KEMKUMHAM_NO, kemkumham_no);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_KEMKUMHAM_TANGGAL, kemkumham_tanggal);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_NOTELP, notelp);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_FAX, fax);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_KETUM, ketum);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_BENDUM, bendum);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_SEKJEN, sekjen);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_BANK_NO, bank_no);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_BANK_NAMA, bank_nama);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_BANK_PEMILIK, bank_pemilik);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_TANGGAL_CD, tanggal_cd);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_TANGGAL_HARDCOPY, tanggal_hardcopy);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_FOTO_PARTAI, foto_partai);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_NAMA, nama);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_VISI, visi);
                                    bundle.putString(Config.BUNDLE_PARTAI_DETAIL_MISI, misi);

                                    Intent intent = new Intent(getApplicationContext(), DetailAnggaranPartaiActivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                }
                            });


                            div.addView(addView);

                            loading.dismiss();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loading.dismiss();
                getData();
                Toast.makeText(AnggaranPartaiActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        div = (LinearLayout) findViewById(R.id.div);
    }
}
