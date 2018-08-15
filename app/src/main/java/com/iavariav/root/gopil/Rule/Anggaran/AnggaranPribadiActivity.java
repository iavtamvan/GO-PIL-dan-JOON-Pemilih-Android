package com.iavariav.root.gopil.Rule.Anggaran;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iavariav.root.gopil.Helper.Config;
import com.iavariav.root.gopil.Model.AnggaranPribadiModel;
import com.iavariav.root.gopil.Model.DapilModels;
import com.iavariav.root.gopil.R;
import com.iavariav.root.gopil.Rest.GAS.ApiServiceGas;
import com.iavariav.root.gopil.Rest.GAS.ClientGas;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnggaranPribadiActivity extends AppCompatActivity {

    private ArrayList<AnggaranPribadiModel> anggaranPribadiModels;
    private ArrayList<DapilModels> dapilModels;
    private LinearLayout div;

    private String wilayah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggaran_pribadi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();

//
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        wilayah = sharedPreferences.getString(Config.SHARED_WILAYAH, "");

        dapilModels = new ArrayList<>();
        anggaranPribadiModels = new ArrayList<>();

//        getDataAnggaranPribadi();

        getdataPaslon();
    }

    private void getdataPaslon() {
        final ProgressDialog loading = ProgressDialog.show(AnggaranPribadiActivity.this, "Loading", "Mengambil data partai...", false, false);
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<DapilModels>> call = apiServiceGas.getDataDapil();
        call.enqueue(new Callback<ArrayList<DapilModels>>() {
            @Override
            public void onResponse(Call<ArrayList<DapilModels>> call, Response<ArrayList<DapilModels>> response) {
                dapilModels = response.body();
                for (final DapilModels s : dapilModels) {
                    if (s.getProvinsi() != null && s.getProvinsi().contains(wilayah)) {
                        final String fotoBersama = s.getFotoBerdua();
                        final String namaKetua = s.getNamaCalon();
                        final String namaWakilKetua = s.getNamaWakliCalon();
                        final String jabatan = s.getJabatan();
                        final String provinsi = s.getProvinsi();
                        final String partaiPengusung = s.getPartaiPengusung();
                        final String visi = s.getVisi();
                        final String misi = s.getMisi();
                        final String program = s.getProgram();
                        final String fotoKetua = s.getFotoKetua();
                        final String fotoWakilKetua = s.getFotoWakilketua();
                        final String pekerjaanKetua = s.getPekerjaanKetua();
                        final String pekerjaanWakilKetua = s.getPekerjaanWakil();

                        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view1 = layoutInflater.inflate(R.layout.row_gopil_list_anggaran_pribadi, null);


                        final CircleImageView cvListAnggaranPribadiImage = view1.findViewById(R.id.cvListAnggaranPribadiImage);
                        Glide.with(getApplicationContext()).load(fotoKetua).error(R.drawable.logo).fitCenter().into(cvListAnggaranPribadiImage);


                        final TextView tvListAnggaranPribadiNamaKetuaPejabat = view1.findViewById(R.id.tvListAnggaranPribadiNamaKetuaPejabat);
                        tvListAnggaranPribadiNamaKetuaPejabat.setText(namaKetua);


                        final TextView tvListAnggaranPribadiJabatan = view1.findViewById(R.id.tvListAnggaranPribadiJabatan);
                        tvListAnggaranPribadiJabatan.setText(pekerjaanKetua);

                        final CardView cvKlikListAnggaranPribadi = view1.findViewById(R.id.cvKlikListAnggaranPribadi);

                        cvKlikListAnggaranPribadi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle bundle = new Bundle();
                                bundle.putString(Config.BUNDLE_DAPIL_DETAIL_NAMA_CALON , tvListAnggaranPribadiNamaKetuaPejabat.getText().toString().trim());
                                Intent intent = new Intent(getApplicationContext(), DetailAnggaranPribadiActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });

                        final TextView tvListAnggaranPribadiNamaWakilKetuaPejabat = view1.findViewById(R.id.tvListAnggaranPribadiNamaWakilKetuaPejabat);
                        tvListAnggaranPribadiNamaWakilKetuaPejabat.setText(namaWakilKetua);

                        final TextView tvListAnggaranPribadiJabatanWakil = view1.findViewById(R.id.tvListAnggaranPribadiJabatanWakil);
                        tvListAnggaranPribadiJabatanWakil.setText(pekerjaanWakilKetua);

                        final CircleImageView cvListAnggaranPribadiImageWakil =  view1.findViewById(R.id.cvListAnggaranPribadiImageWakil);
                        Glide.with(getApplicationContext()).load(fotoWakilKetua).error(R.drawable.logo).crossFade().into(cvListAnggaranPribadiImageWakil);


                        final CardView cvKlikListAnggaranPribadiWakil = view1.findViewById(R.id.cvKlikListAnggaranPribadiWakil);
                        cvKlikListAnggaranPribadiWakil.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle bundle = new Bundle();
                                bundle.putString(Config.BUNDLE_DAPIL_DETAIL_NAMA_CALON , tvListAnggaranPribadiNamaWakilKetuaPejabat.getText().toString().trim());
                                Intent intent = new Intent(getApplicationContext(), DetailAnggaranPribadiActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                        loading.dismiss();

                        div.addView(view1);
                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<DapilModels>> call, Throwable t) {
                loading.dismiss();
                getdataPaslon();
                Toast.makeText(AnggaranPribadiActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataAnggaranPribadi() {
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<AnggaranPribadiModel>> call = apiServiceGas.getDataAnggaranPribadi();
        call.enqueue(new Callback<ArrayList<AnggaranPribadiModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AnggaranPribadiModel>> call, Response<ArrayList<AnggaranPribadiModel>> response) {
                anggaranPribadiModels = response.body();
                for (AnggaranPribadiModel s : anggaranPribadiModels){
                    if (s.getWilayah() != null && s.getWilayah().contains(wilayah)){
                        String jenisHarta = s.getJenisHarta();
                        String keterangan = s.getKeterangan();
                        String nilai = s.getNilai();
                        String foto = s.getFoto();
                        String namaCalon = s.getNamaCalon();
                        String wilayah = s.getWilayah();
                        String jenisAnggaran = s.getJenisAnggaran();


                        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View addView = layoutInflater.inflate(R.layout.row_gopil_vote, null);

                        final RadioButton rbVoteID = addView.findViewById(R.id.rbVoteID);
                        rbVoteID.setVisibility(View.GONE);

                        final ImageView cvVoteImage = addView.findViewById(R.id.cvVoteImage);
                        Glide.with(getApplicationContext()).load(foto).error(R.drawable.logo).fitCenter().into(cvVoteImage);

                        final TextView tvVoteNamaKetuaPejabat = addView.findViewById(R.id.tvVoteNamaKetuaPejabat);
                        tvVoteNamaKetuaPejabat.setText(namaCalon);

                        final TextView tvVoteNamaWakilPejabat = addView.findViewById(R.id.tvVoteNamaWakilPejabat);
                        tvVoteNamaWakilPejabat.setText(wilayah);

                        final TextView tvVoteJabatan = addView.findViewById(R.id.tvVoteJabatan);
                        tvVoteJabatan.setText(jenisAnggaran);

                        final LinearLayout containerVote = addView.findViewById(R.id.containerVote);
                        containerVote.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                        div.addView(addView);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AnggaranPribadiModel>> call, Throwable t) {

            }
        });
    }

    private void initView() {
        div = (LinearLayout) findViewById(R.id.div);
    }
}
