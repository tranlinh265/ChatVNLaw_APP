package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper;

import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tranlinh on 22/02/2018.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static Retrofit retrofitWithToken = null;

    public static Retrofit getClient(String baseUrl){
        if(retrofit == null){
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
                            .addHeader("x-api-token", Define.RailServer.X_API_KEY)
                            .build();
                    return chain.proceed(request);
                }
            });

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
