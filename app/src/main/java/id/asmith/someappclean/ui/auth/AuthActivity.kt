package id.asmith.someappclean.ui.auth

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import id.asmith.someappclean.R
import id.asmith.someappclean.SomeApp
import id.asmith.someappclean.ui.auth.fragment.AuthForgotFragment
import id.asmith.someappclean.ui.auth.fragment.AuthLockFragment
import id.asmith.someappclean.ui.auth.fragment.AuthSigninFragment
import id.asmith.someappclean.ui.auth.fragment.AuthSignupFragment
import id.asmith.someappclean.ui.splash.SplashActivity
import id.asmith.someappclean.utils.AppConstants.USER_KEY
import id.asmith.someappclean.utils.PreferencesUtil
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by Agus Adhi Sumitro on 27/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class AuthActivity : AppCompatActivity(), AuthNavigation {

    @Inject
    lateinit var mPrefsUtil: PreferencesUtil

    private val mViewModel: AuthViewModel by lazy {
        ViewModelProviders.of(this).get(AuthViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        inject()

        if (savedInstanceState == null) {

            mViewModel.setNavigator(this)
            mViewModel.fragmentTransition(userStatus())

        }

    }


    private fun userStatus(): Boolean {
        return true
    }

    override fun rememberUser(): Boolean{

        return mPrefsUtil
                .putRememberUser(
                        USER_KEY,
                        true
                )

    }

    override fun startMainActivity(){

        startActivity<SplashActivity>()
        finish()

    }

    override fun replaceWithLockFragment() = replaceFragment(AuthLockFragment())

    override fun replaceWithSigninFragment() = replaceFragment(AuthSigninFragment())

    override fun replaceWithSignupFragment() = replaceFragment(AuthSignupFragment())

    override fun replaceWithForgotFragment() = replaceFragment(AuthForgotFragment())

    override fun tst() = toast("onPressed")

    private fun replaceFragment (fragment: Fragment, cleanStack: Boolean = false) {

        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) clearBackStack()
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.replace(R.id.fragment_container, fragment)
        ft.addToBackStack(null)
        ft.commit()

    }

    private fun clearBackStack() {

        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

    }

    override fun onBackPressed() {

        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }

    }

    private fun inject() {

        SomeApp
                .mInstance
                .mAppComponent
                .inject(this)

    }

}