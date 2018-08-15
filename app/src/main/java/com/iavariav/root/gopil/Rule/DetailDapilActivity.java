package com.iavariav.root.gopil.Rule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iavariav.root.gopil.Helper.Config;
import com.iavariav.root.gopil.R;

public class DetailDapilActivity extends AppCompatActivity {

    String provinsi;
    String wilayah_id;
    String jabatan;
    String nama_calon;
    String nama_wakli_calon;
    String partai_pengusung;
    String foto_berdua;
    String visi;
    String misi;
    String program;
    String detailProgram;
    String tempatTanggalLahirKetua;
    String tempatTanggalLahirWakilKetua;
    String pekerjaanKetua;
    String pekerjaanWakilKetua;
    String statusPetahana;
    private AppBarLayout appBar;
    private CollapsingToolbarLayout toolbarLayout;
    private ImageView ivDetailDapil;
    private Toolbar toolbar;
    private TextView tvDapilDetailNamaWakil;
    private TextView tvDapilDetailJabatan;
    private TextView tvDapilDetailPartaiPengusung;
    private TextView tvDapilDetailVisi;
    private TextView tvDapilDetailMisi;
    private TextView tvDapilDetailProgram;
    private TextView tvDapilDetailDetailProgram;
    private TextView tvDapilDetailTTLKetua;
    private TextView tvDapilDetailTTLWakilKetua;
    private TextView tvDapilDetailPekerjaanKetua;
    private TextView tvDapilDetailPekerjaanWakilKetua;
    private TextView tvDapilDetailStatusPetahana;
    private FloatingActionButton fab;
    private TextView tvDapilDetailNamaKetua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        provinsi = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_PROVINSI);
        wilayah_id = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_WILAYAH_ID);
        jabatan = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_JABATAN);
        nama_calon = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_NAMA_CALON);
        nama_wakli_calon = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_NAMA_WAKLI_CALON);
        partai_pengusung = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_PARTAI_PENGUSUNG);
        foto_berdua = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_FOTO_BERDUA);
        visi = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_VISI);
        misi = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_MISI);
        program = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_PROGRAM);
        detailProgram = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_DETAILPROGRAM);
        tempatTanggalLahirKetua = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_TEMPATTANGGALLAHIRKETUA);
        tempatTanggalLahirWakilKetua = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_TEMPATTANGGALLAHIRWAKILKETUA);
        pekerjaanKetua = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_PEKERJAANKETUA);
        pekerjaanWakilKetua = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_PEKERJAANWAKILKETUA);
        statusPetahana = bundle.getString(Config.BUNDLE_DAPIL_DETAIL_STATUSPETAHANA);

        getSupportActionBar().setTitle(nama_calon);
//        toolbar.setTitle(nama_calon);
        tvDapilDetailNamaKetua.setText(nama_calon);
        Glide.with(getApplicationContext()).load(foto_berdua).error(R.drawable.logo).fitCenter().into(ivDetailDapil);
        tvDapilDetailNamaWakil.setText(nama_wakli_calon);
        tvDapilDetailJabatan.setText(jabatan);
        tvDapilDetailPartaiPengusung.setText(partai_pengusung);
        tvDapilDetailVisi.setText(visi);
        tvDapilDetailMisi.setText(misi);
        tvDapilDetailProgram.setText(program);
        tvDapilDetailDetailProgram.setText(detailProgram);
        tvDapilDetailTTLKetua.setText(tempatTanggalLahirKetua);
        tvDapilDetailTTLWakilKetua.setText(tempatTanggalLahirWakilKetua);
        tvDapilDetailPekerjaanKetua.setText(pekerjaanKetua);
        tvDapilDetailPekerjaanWakilKetua.setText(pekerjaanWakilKetua);
        tvDapilDetailStatusPetahana.setText(statusPetahana);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    private void initView() {
        appBar = (AppBarLayout) findViewById(R.id.app_bar);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ivDetailDapil = (ImageView) findViewById(R.id.ivDetailDapil);
        tvDapilDetailNamaWakil = (TextView) findViewById(R.id.tvDapilDetailNamaWakil);
        tvDapilDetailJabatan = (TextView) findViewById(R.id.tvDapilDetailJabatan);
        tvDapilDetailPartaiPengusung = (TextView) findViewById(R.id.tvDapilDetailPartaiPengusung);
        tvDapilDetailVisi = (TextView) findViewById(R.id.tvDapilDetailVisi);
        tvDapilDetailMisi = (TextView) findViewById(R.id.tvDapilDetailMisi);
        tvDapilDetailProgram = (TextView) findViewById(R.id.tvDapilDetailProgram);
        tvDapilDetailDetailProgram = (TextView) findViewById(R.id.tvDapilDetailDetailProgram);
        tvDapilDetailTTLKetua = (TextView) findViewById(R.id.tvDapilDetailTTLKetua);
        tvDapilDetailTTLWakilKetua = (TextView) findViewById(R.id.tvDapilDetailTTLWakilKetua);
        tvDapilDetailPekerjaanKetua = (TextView) findViewById(R.id.tvDapilDetailPekerjaanKetua);
        tvDapilDetailPekerjaanWakilKetua = (TextView) findViewById(R.id.tvDapilDetailPekerjaanWakilKetua);
        tvDapilDetailStatusPetahana = (TextView) findViewById(R.id.tvDapilDetailStatusPetahana);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        tvDapilDetailNamaKetua = (TextView) findViewById(R.id.tvDapilDetailNamaKetua);
    }
}
