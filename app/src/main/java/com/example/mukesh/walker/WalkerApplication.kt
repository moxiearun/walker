package com.example.mukesh.walker

import android.app.Application
import com.example.mukesh.walker.network.Urls
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WalkerApplication : Application() {

    companion object {
        fun getRetrofit() {
            val retrofit: Retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Urls.BASE_URL).build()
        }
    }

}