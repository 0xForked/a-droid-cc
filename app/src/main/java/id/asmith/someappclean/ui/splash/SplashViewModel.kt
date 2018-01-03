package id.asmith.someappclean.ui.splash

import id.asmith.someappclean.ui.base.BaseViewModel
import java.util.*


/**
 * Created by Agus Adhi Sumitro on 02/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class SplashViewModel : BaseViewModel<SplashNavigation>() {

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

            getNavigator()?.openMainActivity()

        } else {

            getNavigator()?.openAuthActivity()

        }

    }

}