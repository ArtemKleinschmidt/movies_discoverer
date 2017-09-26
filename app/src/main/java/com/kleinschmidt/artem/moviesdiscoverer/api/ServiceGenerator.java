package com.kleinschmidt.artem.moviesdiscoverer.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Artem Kleinschmidt on 26.09.2017.
 */

public class ServiceGenerator {

    private static final String BASE_URL = "https://api.github.com/";

    private static ServiceGenerator serviceGenerator;

    private Retrofit retrofit;

    public ServiceGenerator() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(httpClient.build())
                        .addConverterFactory(MoshiConverterFactory.create());
        retrofit = builder.build();
    }

    public static ServiceGenerator getServiceGenerator() {
        if (serviceGenerator == null) {
            serviceGenerator = new ServiceGenerator();
        }
        return serviceGenerator;
    }

    public <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
