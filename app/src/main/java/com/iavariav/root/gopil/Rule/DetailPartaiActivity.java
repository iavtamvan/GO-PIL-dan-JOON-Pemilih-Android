package com.iavariav.root.gopil.Rule;

import android.content.Intent;
import android.os.Bundle;
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

public class DetailPartaiActivity extends AppCompatActivity {

    String singkatan;
    String alamat;
    String date;
    String notaris_no;
    String notaris_nama;
    String notaris_tanggal_pendirian;
    String kemkumham_no;
    String kemkumham_tanggal;
    String notelp;
    String fax;
    String ketum;
    String bendum;
    String sekjen;
    String bank_no;
    String bank_nama;
    String bank_pemilik;
    String tanggal_cd;
    String tanggal_hardcopy;
    String foto_partai;
    String nama;
    String visi;
    String misi;
    private TextView tvDetailPartaiKetuaUmum;
    private TextView tvDetailPartaiWakilKetuaUmum;
    private TextView tvDetailPartaiSekretarisJendral;
    private TextView tvDetailPartaiBendaharaUmum;
    private TextView tvDetailPartaiAlamat;
    private TextView tvDetailPartaiNoTelp;
    private TextView tvDetailPartaiVisi;
    private TextView tvDetailPartaiMisi;
    private TextView tvDetailPartaiNotarisTanggalPendirian;
    private TextView tvDetailPartaiNotarisKemkumhamNomor;
    private TextView tvDetailPartaiNotarisKemkumhamTanggal;
    private TextView tvDetailPartaiNotarisTanggalHardCopy;
    private ImageView ivDetailPartai;
    private Toolbar toolbar;
    private TextView tvDetailPartaiNamaPartai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_partai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        singkatan = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_SINGKATAN);
        alamat = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_ALAMAT);
        date = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_DATE);
        notaris_no = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_NOTARIS_NO);
        notaris_nama = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_NOTARIS_NAMA);
        notaris_tanggal_pendirian = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_NOTARIS_TANGGAL_PENDIRIAN);
        kemkumham_no = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_KEMKUMHAM_NO);
        kemkumham_tanggal = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_KEMKUMHAM_TANGGAL);
        notelp = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_NOTELP);
        fax = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_FAX);
        ketum = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_KETUM);
        bendum = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_BENDUM);
        sekjen = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_SEKJEN);
        bank_no = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_BANK_NO);
        bank_nama = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_BANK_NAMA);
        bank_pemilik = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_BANK_PEMILIK);
        tanggal_cd = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_TANGGAL_CD);
        tanggal_hardcopy = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_TANGGAL_HARDCOPY);
        foto_partai = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_FOTO_PARTAI);
        nama = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_NAMA);
        visi = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_VISI);
        misi = bundle.getString(Config.BUNDLE_PARTAI_DETAIL_MISI);

        getSupportActionBar().setTitle(singkatan);
        Glide.with(getApplicationContext()).load(foto_partai).error(R.drawable.logo).fitCenter().into(ivDetailPartai);
        tvDetailPartaiNamaPartai.setText(nama);
        tvDetailPartaiKetuaUmum.setText(ketum);
        tvDetailPartaiSekretarisJendral.setText(sekjen);
        tvDetailPartaiBendaharaUmum.setText(bendum);
        tvDetailPartaiAlamat.setText(alamat);
        tvDetailPartaiNoTelp.setText(notelp);
        tvDetailPartaiVisi.setText(visi);
        tvDetailPartaiMisi.setText(misi);
        tvDetailPartaiNotarisTanggalPendirian.setText(notaris_tanggal_pendirian);
        tvDetailPartaiNotarisKemkumhamNomor.setText(kemkumham_no);
        tvDetailPartaiNotarisKemkumhamTanggal.setText(kemkumham_tanggal);
        tvDetailPartaiNotarisTanggalHardCopy.setText(tanggal_hardcopy);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initView() {
        tvDetailPartaiKetuaUmum = (TextView) findViewById(R.id.tvDetailPartaiKetuaUmum);
        tvDetailPartaiWakilKetuaUmum = (TextView) findViewById(R.id.tvDetailPartaiWakilKetuaUmum);
        tvDetailPartaiSekretarisJendral = (TextView) findViewById(R.id.tvDetailPartaiSekretarisJendral);
        tvDetailPartaiBendaharaUmum = (TextView) findViewById(R.id.tvDetailPartaiBendaharaUmum);
        tvDetailPartaiAlamat = (TextView) findViewById(R.id.tvDetailPartaiAlamat);
        tvDetailPartaiNoTelp = (TextView) findViewById(R.id.tvDetailPartaiNoTelp);
        tvDetailPartaiVisi = (TextView) findViewById(R.id.tvDetailPartaiVisi);
        tvDetailPartaiMisi = (TextView) findViewById(R.id.tvDetailPartaiMisi);
        tvDetailPartaiNotarisTanggalPendirian = (TextView) findViewById(R.id.tvDetailPartaiNotarisTanggalPendirian);
        tvDetailPartaiNotarisKemkumhamNomor = (TextView) findViewById(R.id.tvDetailPartaiNotarisKemkumhamNomor);
        tvDetailPartaiNotarisKemkumhamTanggal = (TextView) findViewById(R.id.tvDetailPartaiNotarisKemkumhamTanggal);
        tvDetailPartaiNotarisTanggalHardCopy = (TextView) findViewById(R.id.tvDetailPartaiNotarisTanggalHardCopy);
        ivDetailPartai = (ImageView) findViewById(R.id.ivDetailPartai);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvDetailPartaiNamaPartai = (TextView) findViewById(R.id.tvDetailPartaiNamaPartai);
    }
}
