package id.asmith.someappclean.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import id.asmith.someappclean.utils.AppConstants as utils

/**
 * Created by Agus Adhi Sumitro on 27/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class ApiModule {

    private val mInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val mClient = OkHttpClient.Builder()
            .addInterceptor(mInterceptor).build()

    private val mApi = Retrofit.Builder()
            .baseUrl(utils.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(mClient)
            .build()

    val mService: ApiService = mApi.create(ApiService::class.java)

}