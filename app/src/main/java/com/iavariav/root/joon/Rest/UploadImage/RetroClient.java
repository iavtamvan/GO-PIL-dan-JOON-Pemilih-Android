package com.iavariav.root.joon.Rest.UploadImage;


import com.iavariav.root.joon.Helper.Config;
import com.iavariav.root.joon.Rest.GAS.ApiServiceGas;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LENOVO on 26/10/2017.
 */

public class RetroClient {


    private static final String ROOT_URL = Config.BASE_URL_IMAGE;

    public RetroClient() {
    }

    private static Retrofit getRetrofitClient() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static ApiServiceGas getService() {
        return getRetrofitClient().create(ApiServiceGas.class);
    }
}
