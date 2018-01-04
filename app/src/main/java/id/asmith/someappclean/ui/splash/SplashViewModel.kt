package id.asmith.someappclean.ui.splash

import android.arch.lifecycle.ViewModel
import java.util.*


/**
 * Created by Agus Adhi Sumitro on 02/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class SplashViewModel : ViewModel() {

    private var mNavigator: SplashNavigation? = null

    fun setNavigator(navigator: SplashNavigation) {
        this.mNavigator = navigator
    }

    fun startTask(isUserStillLogin: Boolean) {

        Timer().schedule(object : TimerTask() {
            override fun run() {
                try {

                    changeActivity(isUserStillLogin)

                } catch (e: Exception) {

                    e.printStackTrace()

                }
            }
        }, 2000)

    }

    private fun changeActivity(isUserStillLogin: Boolean) {

        if (isUserStillLogin) {

            mNavigator!!.openMainActivity()

        } else {

            mNavigator!!.openAuthActivity()

        }

    }

}