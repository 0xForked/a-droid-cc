package id.asmith.someappclean.di

import dagger.Component
import id.asmith.someappclean.ui.auth.AuthActivity
import id.asmith.someappclean.ui.main.MainActivity
import id.asmith.someappclean.ui.splash.SplashActivity
import id.asmith.someappclean.utils.PrefsUtil
import javax.inject.Singleton

/**
 * Created by Agus Adhi Sumitro on 30/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun getPrefsUtil(): PrefsUtil

    fun inject(target: MainActivity)

    fun inject(target: SplashActivity)

    fun inject(target: AuthActivity)

}