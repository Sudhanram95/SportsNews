package com.sudhan.assignments.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit




class NetworkUtil {
    companion object {
        var retrofit: Retrofit? = null
        var okHttpClient: OkHttpClient? = null
        val TIMEOUT:Long = 60

        fun retrofitHelper(): Retrofit? {
            if (okHttpClient == null) {
                val httpClient = OkHttpClient().newBuilder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                httpClient.addInterceptor(interceptor)

                okHttpClient = httpClient.build()
            }

            if(retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://www.balldontlie.io/api/v1/")
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}