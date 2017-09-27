package com.kleinschmidt.artem.moviesdiscoverer.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Artem Kleinschmidt on 26.09.2017.
 */

public class ServiceGenerator {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "api_key";
    private static final String API_KEY_VALUE = "17d6fa4d3f865c897fc92f7ebfc88ead";

    private static ServiceGenerator serviceGenerator;

    private Retrofit retrofit;

    ServiceGenerator() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        addApiKeyInterceptor(httpClient);
        addLoggingInterceptor(httpClient);
        configureTimeout(httpClient);

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
    }

    private void configureTimeout(OkHttpClient.Builder httpClient) {
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
    }

    private void addApiKeyInterceptor(OkHttpClient.Builder httpClient) {
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter(API_KEY, API_KEY_VALUE)
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
    }

    private void addLoggingInterceptor(OkHttpClient.Builder httpClient) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);
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
