package com.iavariav.root.gopil.Rule.Fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iavariav.root.gopil.Helper.Config;
import com.iavariav.root.gopil.Model.DaftarNewsModel;
import com.iavariav.root.gopil.Model.NewsModel;
import com.iavariav.root.gopil.R;
import com.iavariav.root.gopil.Rest.GAS.ApiServiceGas;
import com.iavariav.root.gopil.Rest.GAS.ClientGas;
import com.iavariav.root.gopil.Rule.DetailNewsActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private ArrayList<NewsModel> newsModels;
    private ArrayList<DaftarNewsModel> daftarNewsModels;
    private LinearLayout div;
    private LinearLayout divFragmentNewsSort;
    private TextView tvFragNewsSort;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initView(view);
        newsModels = new ArrayList<>();
        daftarNewsModels = new ArrayList<>();

        divFragmentNewsSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBoxSortBy();
            }
        });
        getDataNewsAll();
        return view;
    }

    private void getDataNewsAll() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading", "Harap tunggu...", false, false);
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<NewsModel>> call = apiServiceGas.getNews();
        call.enqueue(new Callback<ArrayList<NewsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<NewsModel>> call, Response<ArrayList<NewsModel>> response) {
                newsModels = response.body();

                for (int i = 0; i < newsModels.size(); i++) {
                    final String linkgambar = newsModels.get(i).getGambarLink();
                    final String judul = newsModels.get(i).getJudul();
                    final String createdby = newsModels.get(i).getCreatedby();
                    final String waktu = newsModels.get(i).getWaktu();
                    final String artikel = newsModels.get(i).getArtikel();

                    LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View addView = layoutInflater.inflate(R.layout.row_gopil_news, null);

                    final ImageView ivRowNews = addView.findViewById(R.id.ivRowNews);
                    Glide.with(getActivity()).load(linkgambar).error(R.drawable.logo).into(ivRowNews);

                    final TextView tvRowNewsJudulTanggal = addView.findViewById(R.id.tvRowNewsJudulTanggal);
                    tvRowNewsJudulTanggal.setText(waktu);

                    final TextView tvRowNewsJudulBerita = addView.findViewById(R.id.tvRowNewsJudulBerita);
                    tvRowNewsJudulBerita.setText(artikel + "...");

                    final TextView tvRowNewsCreatedBy = addView.findViewById(R.id.tvRowNewsCreatedBy);
                    tvRowNewsCreatedBy.setText(createdby);

                    final TextView tvRowNewsJudulNews = addView.findViewById(R.id.tvRowNewsJudulNews);
                    tvRowNewsJudulNews.setText(judul);

                    final CardView cvKlikDetailNews = addView.findViewById(R.id.cvKlikDetailNews);
                    cvKlikDetailNews.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putString(Config.BUNDLE_NEWS_DETAIL_GAMBAR, linkgambar);
                            bundle.putString(Config.BUNDLE_NEWS_DETAIL_JUDUL, judul);
                            bundle.putString(Config.BUNDLE_NEWS_DETAIL_CREATEDBY, createdby);
                            bundle.putString(Config.BUNDLE_NEWS_DETAIL_TANGGAL, waktu);
                            bundle.putString(Config.BUNDLE_NEWS_DETAIL_ARTIKEL, artikel);
                            Intent intent = new Intent(getActivity(), DetailNewsActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });

                    loading.dismiss();

                    div.addView(addView);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<NewsModel>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getActivity(), "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dialogBoxSortBy() {
        final CharSequence[] itemDaftarNews = {"Politik", "Politik Uang", "Politik Identitas","Kampanye", "Partai", "Edukasi", "Sara"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Urut berdasarkan");
        builder.setItems(itemDaftarNews, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvFragNewsSort.setText(itemDaftarNews[i]);
                getDataNews(true);
            }
        }).show();
    }

    private void getDataNews(boolean rmv) {
        if (rmv) {
            if (div.getChildCount() > 0) div.removeAllViews();
        }
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading", "Harap tunggu...", false, false);
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<NewsModel>> call = apiServiceGas.getNews();
        call.enqueue(new Callback<ArrayList<NewsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<NewsModel>> call, Response<ArrayList<NewsModel>> response) {
                newsModels = response.body();
                if (newsModels == null){
                    Toast.makeText(getActivity(), "" + Config.ERROR_NULL_DATA, Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    for (NewsModel s : newsModels) {
                        if (s.getJenis_berita() != null && s.getJenis_berita().contains(tvFragNewsSort.getText().toString().trim())){
                            final String linkgambar = s.getGambarLink();
                            final String judul = s.getJudul();
                            final String createdby = s.getCreatedby();
                            final String waktu = s.getWaktu();
                            final String artikel = s.getArtikel();

                            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View addView = layoutInflater.inflate(R.layout.row_gopil_news, null);

                            final ImageView ivRowNews = addView.findViewById(R.id.ivRowNews);
                            Glide.with(getActivity()).load(linkgambar).error(R.drawable.logo).crossFade().into(ivRowNews);

                            final TextView tvRowNewsJudulTanggal = addView.findViewById(R.id.tvRowNewsJudulTanggal);
                            tvRowNewsJudulTanggal.setText(waktu);

                            final TextView tvRowNewsJudulBerita = addView.findViewById(R.id.tvRowNewsJudulBerita);
                            tvRowNewsJudulBerita.setText(artikel + "...");

                            final TextView tvRowNewsCreatedBy = addView.findViewById(R.id.tvRowNewsCreatedBy);
                            tvRowNewsCreatedBy.setText(createdby);

                            final TextView tvRowNewsJudulNews = addView.findViewById(R.id.tvRowNewsJudulNews);
                            tvRowNewsJudulNews.setText(judul);

                            final CardView cvKlikDetailNews = addView.findViewById(R.id.cvKlikDetailNews);
                            cvKlikDetailNews.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString(Config.BUNDLE_NEWS_DETAIL_GAMBAR, linkgambar);
                                    bundle.putString(Config.BUNDLE_NEWS_DETAIL_JUDUL, judul);
                                    bundle.putString(Config.BUNDLE_NEWS_DETAIL_CREATEDBY, createdby);
                                    bundle.putString(Config.BUNDLE_NEWS_DETAIL_TANGGAL, waktu);
                                    bundle.putString(Config.BUNDLE_NEWS_DETAIL_ARTIKEL, artikel);
                                    Intent intent = new Intent(getActivity(), DetailNewsActivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });

                            loading.dismiss();

                            div.addView(addView);
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<NewsModel>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getActivity(), "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initView(View view) {
        div = (LinearLayout) view.findViewById(R.id.div);
        divFragmentNewsSort = view.findViewById(R.id.divFragmentNewsSort);
        tvFragNewsSort = view.findViewById(R.id.tvFragNewsSort);
    }
}
