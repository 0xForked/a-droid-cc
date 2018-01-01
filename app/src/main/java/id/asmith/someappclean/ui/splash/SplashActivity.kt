package id.asmith.someappclean.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import id.asmith.someappclean.R
import id.asmith.someappclean.SomeApp
import id.asmith.someappclean.ui.auth.AuthActivity
import id.asmith.someappclean.ui.main.MainActivity
import id.asmith.someappclean.utils.PrefsUtil
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var mPrefsUtil: PrefsUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        injectSplash()

        val loggedStatus = mPrefsUtil
                .getBooleanLogged(
                        "logged",
                        false
                )

        //Do thread
        val background = object : Thread() {
            override fun run() {
                try {

                    // Thread will sleep for 2 seconds
                    sleep((2 * 1000).toLong())

                    if (loggedStatus) {
                        openMainActivity()
                        Log.d("SPLASH", "Main Activity")
                    } else {
                        openAuthActivity()
                        Log.d("SPLASH", "Auth Activity")
                    }

                    finish()

                } catch (e: Exception) {

                    //Print error in logcat (development requirement)
                    e.printStackTrace()

                }
            }
        }

        // start thread
        background.start()

    }

    fun openAuthActivity() {

        //redirect to Auth Activity
        startActivity<AuthActivity>()

    }

    fun openMainActivity() {

        //redirect to Main Activity
        startActivity<MainActivity>()

    }

    private fun injectSplash() {

        //Inject with Dagger
        SomeApp
                .mInstance
                .mAppComponent
                .inject(this)

    }


}
