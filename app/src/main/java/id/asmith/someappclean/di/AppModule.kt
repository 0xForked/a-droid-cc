package id.asmith.someappclean.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import id.asmith.someappclean.SomeApp
import id.asmith.someappclean.utils.PrefsUtil
import javax.inject.Singleton


/**
 * Created by Agus Adhi Sumitro on 30/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */


@Module
class AppModule(private val mApp: SomeApp) {

    @Provides @Singleton
    fun provideApplication() = mApp

    @Provides
    @Singleton
    fun providePreferencesUtil(sharedPreferences: SharedPreferences):
            PrefsUtil = PrefsUtil(sharedPreferences)

    @Provides @Singleton
    fun provideSharedPreferences(): SharedPreferences = provideApplication()
            .getSharedPreferences("", Context.MODE_PRIVATE)

}