package com.iavariav.root.joon.Rule.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.iavariav.root.joon.Helper.Config;
import com.iavariav.root.joon.Model.SliderModel;
import com.iavariav.root.joon.R;
import com.iavariav.root.joon.Rest.GAS.ApiServiceGas;
import com.iavariav.root.joon.Rest.GAS.ClientGas;
import com.iavariav.root.joon.Rule.Anggaran.DaftarAnggaranActivity;
import com.iavariav.root.joon.Rule.DapilActivity;
import com.iavariav.root.joon.Rule.FAQActivity;
import com.iavariav.root.joon.Rule.HomeActivity;
import com.iavariav.root.joon.Rule.IklanListActivity;
import com.iavariav.root.joon.Rule.LaporanAkhirPemilihActivity;
import com.iavariav.root.joon.Rule.PantauKampanyeActivity;
import com.iavariav.root.joon.Rule.PartaiActivity;
import com.iavariav.root.joon.Rule.ProfilActivity;
import com.iavariav.root.joon.Rule.TahapanPemiluActivity;
import com.iavariav.root.joon.Rule.TataCaraActivity;
import com.iavariav.root.joon.Rule.TataTertibActivity;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LainnyaFragment extends Fragment implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener {


    private ArrayList<SliderModel> sliderModels;
    private SliderLayout mSliderSlider;
    private PagerIndicator customIndicator;
    private PagerIndicator customIndicator2;
    private CardView cvLainnyaQProfil;
    private CardView cvLainnyaProfil;
    private CardView cvKlikPartai;
    private CardView cvKlikDapil;
    private CardView cvLainnyaAnggaran;
    private CardView cvKlikTahapanPemilu;
    private CardView cvKlikTataTertib;
    private CardView cvKlikTataCara;
    private CardView cvKlikFAQ;
    private CardView cvKlikIklan;
    private CardView cvKlikLaporanAkhir;
    private CardView cvKlikPantauKampanye;

    public LainnyaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lainnya, container, false);
        ((HomeActivity) getActivity()).getSupportActionBar().setLogo(R.drawable.arrow);
        initView(view);

        getSlider();

        cvLainnyaProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProfilActivity.class));
            }
        });

        cvKlikPartai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PartaiActivity.class));
            }
        });

        cvKlikDapil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DapilActivity.class));
            }
        });
        cvLainnyaAnggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DaftarAnggaranActivity.class));
            }
        });

        cvKlikTahapanPemilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TahapanPemiluActivity.class));
            }
        });

        cvKlikTataTertib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TataTertibActivity.class));
            }
        });

        cvKlikTataCara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TataCaraActivity.class));
            }
        });

        cvKlikFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FAQActivity.class));
            }
        });

        cvKlikIklan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), IklanListActivity.class));
            }
        });

        cvKlikLaporanAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LaporanAkhirPemilihActivity.class));
            }
        });

        cvKlikPantauKampanye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PantauKampanyeActivity.class));
            }
        });


        return view;
    }


    private void getSlider() {
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<SliderModel>> call = apiServiceGas.getDataSliderOnline();
        call.enqueue(new Callback<ArrayList<SliderModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SliderModel>> call, Response<ArrayList<SliderModel>> response) {
                sliderModels = response.body();
                for (int i = 0; i < sliderModels.size(); i++) {

                    HashMap<String, String> url_maps = new HashMap<String, String>();
                    // * Get internet
                    url_maps.put(sliderModels.get(i).getNama(), sliderModels.get(i).getLink());
                    // * Get internet

//        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
//        file_maps.put("Himpunan Mahasiswa Informatika", R.drawable.hm);
//        file_maps.put("Semicolon", R.drawable.semicolon);
//        file_maps.put("Himpunan Mahasiswa Informatika", R.drawable.hm);
//        file_maps.put("Himpunan Mahasiswa Informatika", R.drawable.semicolon);

                    for (String name : url_maps.keySet()) {
                        TextSliderView textSliderView = new TextSliderView(getActivity());
                        // initialize a SliderLayout
                        textSliderView
                                .description(name)
                                .image(url_maps.get(name))
                                .setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
                        //add your extra information
                        textSliderView.bundle(new Bundle());
                        textSliderView.getBundle()
                                .putString("extra", name);

                        mSliderSlider.addSlider(textSliderView);
                    }
                    mSliderSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                    mSliderSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                    mSliderSlider.setCustomAnimation(new DescriptionAnimation());
                    mSliderSlider.setDuration(4000);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SliderModel>> call, Throwable t) {
                Toast.makeText(getActivity(), "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initView(View view) {
        mSliderSlider = (SliderLayout) view.findViewById(R.id.mSliderSlider);
        customIndicator = (PagerIndicator) view.findViewById(R.id.custom_indicator);
        customIndicator2 = (PagerIndicator) view.findViewById(R.id.custom_indicator2);
        cvLainnyaProfil = (CardView) view.findViewById(R.id.cvLainnyaProfil);
        cvKlikPartai = (CardView) view.findViewById(R.id.cvKlikPartai);
        cvKlikDapil = (CardView) view.findViewById(R.id.cvKlikDapil);
        cvLainnyaAnggaran = (CardView) view.findViewById(R.id.cvLainnyaAnggaran);
        cvKlikTahapanPemilu = (CardView) view.findViewById(R.id.cvKlikTahapanPemilu);
        cvKlikTataTertib = (CardView) view.findViewById(R.id.cvKlikTataTertib);
        cvKlikTataCara = (CardView) view.findViewById(R.id.cvKlikTataCara);
        cvKlikFAQ = (CardView) view.findViewById(R.id.cvKlikFAQ);
        cvKlikIklan = (CardView) view.findViewById(R.id.cvKlikIklan);
        cvKlikLaporanAkhir = (CardView) view.findViewById(R.id.cvKlikLaporanAkhir);
        cvKlikPantauKampanye = (CardView) view.findViewById(R.id.cvKlikPantauKampanye);
    }
}
