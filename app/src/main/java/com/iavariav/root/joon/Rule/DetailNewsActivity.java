package com.iavariav.root.joon.Rule;

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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.iavariav.root.joon.Helper.Config;
import com.iavariav.root.joon.R;

public class DetailNewsActivity extends AppCompatActivity {

    private AppBarLayout appBar;
    private CollapsingToolbarLayout toolbarLayout;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ImageView ivDetailNews;
    private TextView tvDetailNewsCreatedBy;
    private TextView tvDetailNewsTanggal;
    private TextView tvDetailNewsBerita;
    private TextView tvDetailNewsBeritaJudul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String linkGambar = bundle.getString(Config.BUNDLE_NEWS_DETAIL_GAMBAR);
        String judul = bundle.getString(Config.BUNDLE_NEWS_DETAIL_JUDUL);
        String createdBy = bundle.getString(Config.BUNDLE_NEWS_DETAIL_CREATEDBY);
        String tanggal = bundle.getString(Config.BUNDLE_NEWS_DETAIL_TANGGAL);
        String artikel = bundle.getString(Config.BUNDLE_NEWS_DETAIL_ARTIKEL);

        Glide.with(getApplicationContext()).load(linkGambar).error(R.drawable.logo).diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .fitCenter()
                .crossFade()
                .into(ivDetailNews);
        toolbar.setTitle(null);

        tvDetailNewsCreatedBy.setText(createdBy);

        tvDetailNewsTanggal.setText(tanggal);

        tvDetailNewsBerita.setText(artikel);

        tvDetailNewsBeritaJudul.setText(judul);


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
        fab = (FloatingActionButton) findViewById(R.id.fab);
        ivDetailNews = (ImageView) findViewById(R.id.ivDetailNews);
        tvDetailNewsCreatedBy = (TextView) findViewById(R.id.tvDetailNewsCreatedBy);
        tvDetailNewsTanggal = (TextView) findViewById(R.id.tvDetailNewsTanggal);
        tvDetailNewsBerita = (TextView) findViewById(R.id.tvDetailNewsBerita);
        tvDetailNewsBeritaJudul = (TextView) findViewById(R.id.tvDetailNewsBeritaJudul);
    }
}
