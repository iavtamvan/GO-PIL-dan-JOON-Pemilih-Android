package com.iavariav.root.joon.Rule.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iavariav.root.joon.Helper.Config;
import com.iavariav.root.joon.Model.ResultVoteModel;
import com.iavariav.root.joon.R;
import com.iavariav.root.joon.Rest.GAS.ApiServiceGas;
import com.iavariav.root.joon.Rest.GAS.ClientGas;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultVoteFragment extends Fragment {
    private ArrayList<ResultVoteModel> resultVoteModels;
    private LinearLayout div;

    private String provinsi, namaCalonKetua, namaCalonWakilKetua, jabatan, suaraVote, fotoBersama;


    public ResultVoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_result_vote, container, false);
        initView(view);

        SharedPreferences sp = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        provinsi = sp.getString(Config.SHARED_WILAYAH, "");

        getDataResultVote();

        return view;
    }


    private void getDataResultVote() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading", "Menghitung suara...", false, false);
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<ResultVoteModel>> call = apiServiceGas.getDataResultVotel();
        call.enqueue(new Callback<ArrayList<ResultVoteModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ResultVoteModel>> call, Response<ArrayList<ResultVoteModel>> response) {
                resultVoteModels = response.body();
                for (ResultVoteModel s : resultVoteModels) {
                    if (s.getProvinsi() != null && s.getProvinsi().contains(provinsi)) {
                        namaCalonKetua = s.getNamaCalon();
                        namaCalonWakilKetua = s.getNamaWakliCalon();
                        jabatan = s.getJabatan();
                        suaraVote = s.getJumlahSuara();
                        fotoBersama = s.getFoto_berdua();

                        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View addView = layoutInflater.inflate(R.layout.row_gopil_result_vote, null);


                        final CircleImageView cvVoteImage = addView.findViewById(R.id.cvDetailVoteImage);
                        Glide.with(getActivity()).load(fotoBersama).error(R.drawable.logo).fitCenter().into(cvVoteImage);

                        final TextView tvResultVoteNamaKetuaPejabat = addView.findViewById(R.id.tvResultVoteNamaKetuaPejabat);
                        tvResultVoteNamaKetuaPejabat.setText(namaCalonKetua);

                        final TextView tvResultVoteNamaWakilPejabat = addView.findViewById(R.id.tvResultVoteNamaWakilPejabat);
                        tvResultVoteNamaWakilPejabat.setText(namaCalonWakilKetua);

                        final TextView tvResultVoteJabatan = addView.findViewById(R.id.tvResultVoteJabatan);
                        tvResultVoteJabatan.setText(jabatan);

                        final TextView tvResultVoteWilayah = addView.findViewById(R.id.tvResultVoteWilayah);
                        tvResultVoteWilayah.setText(provinsi);

                        final TextView tvResultVoteSuara = addView.findViewById(R.id.tvResultVoteSuara);
                        tvResultVoteSuara.setText(suaraVote + " suara");

                        div.addView(addView);

                        loading.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResultVoteModel>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getActivity(), "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(View view) {
        div = (LinearLayout) view.findViewById(R.id.div);
    }

}