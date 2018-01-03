package id.asmith.someappclean.di

import dagger.Component
import id.asmith.someappclean.di.module.AppModule
import id.asmith.someappclean.di.module.RemoteModule
import id.asmith.someappclean.di.module.UIModule
import id.asmith.someappclean.ui.auth.AuthActivity
import id.asmith.someappclean.ui.main.MainActivity
import id.asmith.someappclean.ui.splash.SplashActivity
import javax.inject.Singleton

/**
 * Created by Agus Adhi Sumitro on 30/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

//Array of module
@Singleton
@Component(modules = [
    (AppModule::class),
    (UIModule::class),
    (RemoteModule::class)
])

interface AppComponent {

    fun inject(target: MainActivity)
    fun inject(target: SplashActivity)
    fun inject(target: AuthActivity)

}