package id.asmith.someappclean.ui.splash

import id.asmith.someappclean.ui.base.BaseViewModel


/**
 * Created by Agus Adhi Sumitro on 02/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class SplashViewModel : BaseViewModel<SplashNavigation>() {

    fun startStream(isUserStillLogin: Boolean) {

        val background = object : Thread() {
            override fun run() {
                try {

                    sleep((2 * 1000).toLong())

                    changeActivity(isUserStillLogin)

                } catch (e: Exception) {

                    e.printStackTrace()

                }
            }
        }

        background.start()

    }

    private fun changeActivity(isUserStillLogin: Boolean) {

        if (isUserStillLogin) {

            getNavigator()?.openMainActivity()

        } else {

            getNavigator()?.openAuthActivity()

        }

    }

}