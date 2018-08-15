package com.iavariav.root.gopil.Rule;

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
import com.iavariav.root.gopil.Model.DapilModels;
import com.iavariav.root.gopil.R;
import com.iavariav.root.gopil.Rest.GAS.ApiServiceGas;
import com.iavariav.root.gopil.Rest.GAS.ClientGas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DapilActivity extends AppCompatActivity {

    private LinearLayout div;

    private ArrayList<DapilModels> dapilModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dapil);
        initView();

        dapilModels = new ArrayList<>();
        getDataDapil();
    }

    private void getDataDapil() {
        final ProgressDialog loading = ProgressDialog.show(DapilActivity.this, "Loading", "Mengambil data dapil...", false, false);

        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<DapilModels>> call = apiServiceGas.getDataDapil();
        call.enqueue(new Callback<ArrayList<DapilModels>>() {
            @Override
            public void onResponse(Call<ArrayList<DapilModels>> call, Response<ArrayList<DapilModels>> response) {
                dapilModels = response.body();
                for (int i = 0; i < dapilModels.size(); i++) {
                    final String provinsi = dapilModels.get(i).getProvinsi();
                    final String wilayah_id = String.valueOf(dapilModels.get(i).getWilayahId());
                    final String jabatan = dapilModels.get(i).getJabatan();
                    final String nama_calon = dapilModels.get(i).getNamaCalon();
                    final String nama_wakli_calon = dapilModels.get(i).getNamaWakliCalon();
                    final String partai_pengusung = dapilModels.get(i).getPartaiPengusung();
                    final String foto_berdua = dapilModels.get(i).getFotoBerdua();
                    final String visi = dapilModels.get(i).getVisi();
                    final String misi = dapilModels.get(i).getMisi();
                    final String program = dapilModels.get(i).getProgram();
                    final String detailProgram = dapilModels.get(i).getDetailsProgram();
                    final String tempatTanggalLahirKetua = dapilModels.get(i).getTempatDanTanggalLahirKetua();
                    final String tempatTanggalLahirWakilKetua = dapilModels.get(i).getTempatDanTanggalLahirWakilketua();
                    final String pekerjaanKetua = dapilModels.get(i).getPekerjaanKetua();
                    final String pekerjaanWakilKetua = dapilModels.get(i).getPekerjaanWakil();
                    final String statusPetahana = dapilModels.get(i).getStatusPetahana();




                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view = layoutInflater.inflate(R.layout.row_gopil_daerah_pemilih, null);


                    final TextView tvRowDapilPartaiPengusung = (TextView) view.findViewById(R.id.tvRowDapilPartaiPengusung);
                    final TextView tvRowDapilProvinsi = (TextView) view.findViewById(R.id.tvRowDapilProvinsi);
                    final TextView tvRowDapilJabatan = (TextView) view.findViewById(R.id.tvRowDapilJabatan);
                    final TextView tvRowDapilNamaWakilKetua = (TextView) view.findViewById(R.id.tvRowDapilNamaWakilKetua);
                    final TextView tvRowDapilNamaKetua = (TextView) view.findViewById(R.id.tvRowDapilNamaKetua);
                    final ImageView ivRowDapil = (ImageView) view.findViewById(R.id.ivRowDapil);

                    final LinearLayout containerDapil = view.findViewById(R.id.containerDapil);
                    containerDapil.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_PROVINSI , provinsi);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_WILAYAH_ID , wilayah_id);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_JABATAN , jabatan);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_NAMA_CALON , nama_calon);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_NAMA_WAKLI_CALON , nama_wakli_calon);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_PARTAI_PENGUSUNG , partai_pengusung);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_FOTO_BERDUA , foto_berdua);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_VISI , visi);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_MISI , misi);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_PROGRAM , program);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_DETAILPROGRAM , detailProgram);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_TEMPATTANGGALLAHIRKETUA , tempatTanggalLahirKetua);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_TEMPATTANGGALLAHIRWAKILKETUA , tempatTanggalLahirWakilKetua);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_PEKERJAANKETUA , pekerjaanKetua);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_PEKERJAANWAKILKETUA , pekerjaanWakilKetua);
                            bundle.putString(Config.BUNDLE_DAPIL_DETAIL_STATUSPETAHANA , statusPetahana);

                            Intent intent = new Intent(getApplicationContext(), DetailDapilActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });

                    if (nama_wakli_calon.isEmpty()){
                        containerDapil.setVisibility(View.GONE);
                    } else {
                        tvRowDapilPartaiPengusung.setText(partai_pengusung);
                        tvRowDapilProvinsi.setText(provinsi);
                        tvRowDapilJabatan.setText(jabatan);
                        tvRowDapilNamaWakilKetua.setText(nama_wakli_calon);
                        tvRowDapilNamaKetua.setText(nama_calon);

                        Glide.with(getApplicationContext()).load(foto_berdua).error(R.drawable.logo).crossFade().into(ivRowDapil);

                        loading.dismiss();


                        div.addView(view);
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<DapilModels>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(DapilActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        div = (LinearLayout) findViewById(R.id.div);
    }
}
