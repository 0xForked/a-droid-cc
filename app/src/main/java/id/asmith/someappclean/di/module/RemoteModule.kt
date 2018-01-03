package id.asmith.someappclean.di.module

import dagger.Module
import dagger.Provides
import id.asmith.someappclean.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import id.asmith.someappclean.utils.AppConstants as utils

/**
 * Created by Agus Adhi Sumitro on 01/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

@Module
class RemoteModule {

    @Provides @Singleton
    fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient
                    .Builder()
                    .addInterceptor(HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()

    @Provides @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(utils.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build()

    @Provides @Singleton
    fun provideService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)

}

