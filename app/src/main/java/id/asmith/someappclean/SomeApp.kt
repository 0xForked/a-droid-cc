package id.asmith.someappclean

import android.app.Application
import id.asmith.someappclean.di.AppComponent
import id.asmith.someappclean.di.DaggerAppComponent
import id.asmith.someappclean.di.module.AppModule


/**
 * Created by Agus Adhi Sumitro on 27/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */


class SomeApp : Application() {

    companion object {
        lateinit var mInstance: SomeApp
    }

    lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mAppComponent = initAppComponent()

    }

    private fun initAppComponent(): AppComponent {
        return DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()

    }

}