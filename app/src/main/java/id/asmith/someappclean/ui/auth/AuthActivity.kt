package id.asmith.someappclean.ui.auth

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import id.asmith.someappclean.R
import id.asmith.someappclean.SomeApp
import id.asmith.someappclean.data.local.LocalDataHandler
import id.asmith.someappclean.data.remote.ApiService
import id.asmith.someappclean.ui.auth.fragment.AuthForgotFragment
import id.asmith.someappclean.ui.auth.fragment.AuthLockFragment
import id.asmith.someappclean.ui.auth.fragment.AuthSigninFragment
import id.asmith.someappclean.ui.auth.fragment.AuthSignupFragment
import id.asmith.someappclean.ui.main.MainActivity
import id.asmith.someappclean.utils.PreferencesUtil
import id.asmith.someappclean.utils.scheduler.BaseSchedulerProvider
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by Agus Adhi Sumitro on 27/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class AuthActivity : AppCompatActivity(), AuthNavigation {

    @Inject lateinit var mPrefsUtil: PreferencesUtil

    @Inject lateinit var mApiService : ApiService

    @Inject lateinit var mScheduler : BaseSchedulerProvider

    private val mViewModel: AuthViewModel by lazy {
        ViewModelProviders.of(this).get(AuthViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        inject()

        savedInstanceState.let {
            mViewModel.setNavigator(this)
            mViewModel.fragmentTransition(userStatus())
        }

        mViewModel.setPrefs(mPrefsUtil)
        mViewModel.setApiService(mApiService)
        mViewModel.setScheduler(mScheduler)
        mViewModel.setDatabaseHelper(LocalDataHandler(this))

    }

    private fun userStatus(): Boolean {

        val userData = LocalDataHandler(this).getUserData()
        val uid = userData["uid"]
        return uid != null

    }

    override fun startMainActivity(){

        startActivity<MainActivity>()
        finish()

    }

    override fun startThisActivity(){

        startActivity<AuthActivity>()
        finish()

    }

    override fun customToast(text: String) = toast(text)

    override fun customProgressDialog() = indeterminateProgressDialog("Please wait...")

    override fun replaceWithLockFragment() = replaceFragment(AuthLockFragment())

    override fun replaceWithSigninFragment() = replaceFragment(AuthSigninFragment())

    override fun replaceWithSignupFragment() = replaceFragment(AuthSignupFragment())

    override fun replaceWithForgotFragment() = replaceFragment(AuthForgotFragment())

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