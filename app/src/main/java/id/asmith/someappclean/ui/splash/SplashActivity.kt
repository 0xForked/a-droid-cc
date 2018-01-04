package id.asmith.someappclean.ui.splash

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.asmith.someappclean.R
import id.asmith.someappclean.SomeApp
import id.asmith.someappclean.ui.auth.AuthActivity
import id.asmith.someappclean.ui.main.MainActivity
import id.asmith.someappclean.utils.AppConstants.USER_LOG_STATUS
import id.asmith.someappclean.utils.PreferencesUtil
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashNavigation {

    @Inject
    lateinit var mPrefsUtil: PreferencesUtil

    private val mViewModel: SplashViewModel by lazy {
        ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        inject()

        mViewModel.setNavigator(this)
        mViewModel.startTask(userStatus())

    }

    override fun openAuthActivity() {

        startActivity<AuthActivity>()
        finish()

    }

    override fun openMainActivity() {

        startActivity<MainActivity>()
        finish()

    }

    private fun userStatus(): Boolean {

        return mPrefsUtil
                .getRememberUser(
                        USER_LOG_STATUS,
                        false
                )

    }

    private fun inject() {

        SomeApp
                .mInstance
                .mAppComponent
                .inject(this)

    }


}
