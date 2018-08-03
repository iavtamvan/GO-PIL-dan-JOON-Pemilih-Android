package com.iavariav.root.joon.Rule.Anggaran;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.iavariav.root.joon.Helper.Config;
import com.iavariav.root.joon.R;

public class DetailAnggaranActivity extends AppCompatActivity {

    private TextView tvDetailAnggaranUsulanAnggaran;
    private TextView tvDetailAnggaranUsulanAnggaranDisejui;
    private TextView tvDetailAnggaranTermin1;
    private TextView tvDetailAnggaranTermin2;
    private TextView tvDetailAnggaranTermin3;
    private TextView tvDetailAnggaranTermin11;
    private TextView tvDetailAnggaranTermin22;
    private TextView tvDetailAnggaranTermin33;
    private TextView tvDetailAnggaranTotal;
    private TextView tvDetailAnggaranNomorNHPD;
    private TextView tvDetailAnggaranTanggalNPHD;

    private String namaSatker;
    private String usulanAnggaran;
    private String usulanAnggaranDisetujui;
    private String termin1;
    private String termin2;
    private String termin3;
    private String termin11;
    private String termin22;
    private String termin33;
    private String total;
    private String nomorNhpd;
    private String tanggalNphd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anggaran);
        initView();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        namaSatker = bundle.getString(Config.BUNDLE_ANGGARAN_DETAIL_NAMASATKER);
        usulanAnggaran = bundle.getString(Config.BUNDLE_ANGGARAN_DETAIL_USULANANGGARAN);
        usulanAnggaranDisetujui = bundle.getString(Config.BUNDLE_ANGGARAN_DETAIL_USULANANGGARANDISETUJUI);
        termin1 = bundle.getString(Config.BUNDLE_ANGGARAN_DETAIL_TERMIN1);
        termin2 = bundle.getString(Config.BUNDLE_ANGGARAN_DETAIL_TERMIN2);
        termin3 = bundle.getString(Config.BUNDLE_ANGGARAN_DETAIL_TERMIN3);
        termin11 = bundle.getString(Config.BUNDLE_ANGGARAN_DETAIL_TERMIN11);
        termin22 = bundle.getString(Config.BUNDLE_ANGGARAN_DETAIL_TERMIN22);
        termin33 = bundle.getString(Config.BUNDLE_ANGGARAN_DETAIL_TERMIN33);
        total = bundle.getString(Config.BUNDLE_ANGGARAN_DETAIL_TOTAL);
        nomorNhpd = bundle.getString(Config.BUNDLE_ANGGARAN_DETAIL_NOMORNHPD);
        tanggalNphd = bundle.getString(Config.BUNDLE_ANGGARAN_DETAIL_TANGGALNPHD);

        tvDetailAnggaranUsulanAnggaran.setText(usulanAnggaran);
        tvDetailAnggaranUsulanAnggaranDisejui.setText(usulanAnggaranDisetujui);
        tvDetailAnggaranTermin1.setText(termin1);
        tvDetailAnggaranTermin2.setText(termin2);
        tvDetailAnggaranTermin3.setText(termin3);
        tvDetailAnggaranTermin11.setText(termin11);
        tvDetailAnggaranTermin22.setText(termin22);
        tvDetailAnggaranTermin33.setText(termin33);
        tvDetailAnggaranTotal.setText(total);
        tvDetailAnggaranNomorNHPD.setText(nomorNhpd);
        tvDetailAnggaranTanggalNPHD.setText(tanggalNphd);



    }

    private void initView() {
        tvDetailAnggaranUsulanAnggaran = (TextView) findViewById(R.id.tvDetailAnggaranUsulanAnggaran);
        tvDetailAnggaranUsulanAnggaranDisejui = (TextView) findViewById(R.id.tvDetailAnggaranUsulanAnggaranDisejui);
        tvDetailAnggaranTermin1 = (TextView) findViewById(R.id.tvDetailAnggaranTermin1);
        tvDetailAnggaranTermin2 = (TextView) findViewById(R.id.tvDetailAnggaranTermin2);
        tvDetailAnggaranTermin3 = (TextView) findViewById(R.id.tvDetailAnggaranTermin3);
        tvDetailAnggaranTermin11 = (TextView) findViewById(R.id.tvDetailAnggaranTermin11);
        tvDetailAnggaranTermin22 = (TextView) findViewById(R.id.tvDetailAnggaranTermin22);
        tvDetailAnggaranTermin33 = (TextView) findViewById(R.id.tvDetailAnggaranTermin33);
        tvDetailAnggaranTotal = (TextView) findViewById(R.id.tvDetailAnggaranTotal);
        tvDetailAnggaranNomorNHPD = (TextView) findViewById(R.id.tvDetailAnggaranNomorNHPD);
        tvDetailAnggaranTanggalNPHD = (TextView) findViewById(R.id.tvDetailAnggaranTanggalNPHD);
    }
}
