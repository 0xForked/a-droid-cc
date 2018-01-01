package id.asmith.someappclean.ui.auth

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.asmith.someappclean.R
import id.asmith.someappclean.SomeApp
import id.asmith.someappclean.ui.splash.SplashActivity
import id.asmith.someappclean.utils.PrefsUtil
import kotlinx.android.synthetic.main.activity_auth.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import javax.inject.Inject


/**
 * Created by Agus Adhi Sumitro on 27/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class AuthActivity : AppCompatActivity() {

    @Inject
    lateinit var mPrefsUtil: PrefsUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        injectAuth()

        login.setOnClickListener {
            mPrefsUtil.putBooleanLogged("logged", true)
            startActivity<SplashActivity>()
            finish()
        }

        val loggedStatus = mPrefsUtil
                .getBooleanLogged(
                        "logged",
                        false
                )

        longToast(loggedStatus.toString())

    }

    private fun injectAuth() {
        SomeApp
                .mInstance
                .mAppComponent
                .inject(this)

    }
}