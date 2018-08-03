package com.iavariav.root.joon.Rule;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iavariav.root.joon.Helper.Config;
import com.iavariav.root.joon.Model.IklanMasyarkatModel;
import com.iavariav.root.joon.R;
import com.iavariav.root.joon.Rest.GAS.ApiServiceGas;
import com.iavariav.root.joon.Rest.GAS.ClientGas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IklanListActivity extends AppCompatActivity {

    private LinearLayout div;
    private ArrayList<IklanMasyarkatModel> iklanMasyarkatModels;
    private String judul, sumber, gambar, linkVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_iklan);
        initView();
        iklanMasyarkatModels = new ArrayList<>();

        getDataVideo();
    }

    private void getDataVideo() {
        final ProgressDialog loading = ProgressDialog.show(IklanListActivity.this, "Loading", "Mengambil data...", false, false);
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<IklanMasyarkatModel>> call = apiServiceGas.getDataIklan();
        call.enqueue(new Callback<ArrayList<IklanMasyarkatModel>>() {
            @Override
            public void onResponse(Call<ArrayList<IklanMasyarkatModel>> call, Response<ArrayList<IklanMasyarkatModel>> response) {
                iklanMasyarkatModels = response.body();
                for (IklanMasyarkatModel s : iklanMasyarkatModels){
                    if (s.getJenis_video() != null && s.getJenis_video().contains("Iklan")){
                        judul = s.getJudul();
                        sumber = s.getSumber();
                        gambar = s.getGambar();
                        linkVideo = s.getLinkVideo();

                        LayoutInflater layoutInflater  = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view = layoutInflater.inflate(R.layout.row_gopil_iklan_masyarakat, null);

                        final ImageView ivRowIklan = view.findViewById(R.id.ivRowIklan);
                        Glide.with(IklanListActivity.this).load(gambar).centerCrop().error(R.drawable.logo).into(ivRowIklan);

                        final TextView tvRowIklanJudul = view.findViewById(R.id.tvRowIklanJudul);
                        tvRowIklanJudul.setText(judul);

                        final TextView tvRowIklanSumber = view.findViewById(R.id.tvRowIklanSumber);
                        tvRowIklanSumber.setText(sumber);

                        final TextView tvRowIklanGambar = view.findViewById(R.id.tvRowIklanGambar);
                        tvRowIklanGambar.setText(gambar);

                        final TextView tvRowIklanLinkVideo = view.findViewById(R.id.tvRowIklanLinkVideo);
                        tvRowIklanLinkVideo.setText(linkVideo);

                        final CardView cvKlikDetailIklan = view.findViewById(R.id.cvKlikDetailIklan);
                        cvKlikDetailIklan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), IklanVideoActivity.class);
                                intent.putExtra(Config.BUNDLE_IKLAN_JUDUL, tvRowIklanJudul.getText().toString());
                                intent.putExtra(Config.BUNDLE_IKLAN_SUMBER, tvRowIklanSumber.getText().toString());
                                intent.putExtra(Config.BUNDLE_IKLAN_LINK, tvRowIklanLinkVideo.getText().toString());
                                intent.putExtra(Config.BUNDLE_IKLAN_GAMBAR, tvRowIklanGambar.getText().toString());
                                startActivity(intent);
                            }
                        });

                        div.addView(view);
                        loading.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<IklanMasyarkatModel>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(IklanListActivity.this, "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        div = (LinearLayout) findViewById(R.id.div);
    }
}
