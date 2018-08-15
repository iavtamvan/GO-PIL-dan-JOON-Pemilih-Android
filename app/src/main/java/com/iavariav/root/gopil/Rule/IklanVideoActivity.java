package com.iavariav.root.gopil.Rule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iavariav.root.gopil.Helper.Config;
import com.iavariav.root.gopil.R;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;

public class IklanVideoActivity extends AppCompatActivity {

    private YouTubePlayerView youtubePlayerView;

    private String judul;
    private String sumber;
    private String linkVideo;
    private String gambar;
    private TextView tvIklanVideoJudul;
    private TextView tvIklanVideoSumber;
    private ImageView ivIklanVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iklan_video);
        getSupportActionBar().hide();
        initView();

        Intent intent = getIntent();
        judul = intent.getStringExtra(Config.BUNDLE_IKLAN_JUDUL);
        sumber = intent.getStringExtra(Config.BUNDLE_IKLAN_SUMBER);
        linkVideo = intent.getStringExtra(Config.BUNDLE_IKLAN_LINK);
        gambar = intent.getStringExtra(Config.BUNDLE_IKLAN_GAMBAR);

        youtubePlayerView.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(final YouTubePlayer youTubePlayer) {
                youTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        youTubePlayer.loadVideo(linkVideo, 0);
                    }
                });
            }
        }, true);

        tvIklanVideoJudul.setText(judul);
        tvIklanVideoSumber.setText(sumber);
        Glide.with(IklanVideoActivity.this).load(gambar).centerCrop().into(ivIklanVideo);
    }

    private void initView() {
        youtubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        tvIklanVideoJudul = (TextView) findViewById(R.id.tvIklanVideoJudul);
        tvIklanVideoSumber = (TextView) findViewById(R.id.tvIklanVideoSumber);
        ivIklanVideo = (ImageView) findViewById(R.id.ivIklanVideo);
    }
}
