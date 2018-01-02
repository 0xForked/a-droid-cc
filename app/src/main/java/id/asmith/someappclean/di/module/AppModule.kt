package id.asmith.someappclean.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import id.asmith.someappclean.SomeApp
import id.asmith.someappclean.utils.PreferencesUtil
import javax.inject.Singleton


/**
 * Created by Agus Adhi Sumitro on 30/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */


@Module
class AppModule(private val someApp: SomeApp) {

    @Provides @Singleton
    fun provideApplication() = someApp

    @Provides @Singleton
    fun providePreferencesUtil(sharedPreferences: SharedPreferences):
            PreferencesUtil = PreferencesUtil(sharedPreferences)

    @Provides @Singleton
    fun provideSharedPreferences(): SharedPreferences = provideApplication()
            .getSharedPreferences("", Context.MODE_PRIVATE)

}