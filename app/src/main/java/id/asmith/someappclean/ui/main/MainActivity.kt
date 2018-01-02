package id.asmith.someappclean.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.asmith.someappclean.R
import id.asmith.someappclean.SomeApp
import id.asmith.someappclean.ui.splash.SplashActivity
import id.asmith.someappclean.utils.PreferencesUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import javax.inject.Inject


/**
 * Created by Agus Adhi Sumitro on 23/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mPrefsUtil: PreferencesUtil

    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectMain()

        mViewModel.getCounter().observe(this, Observer {
            textName.text = it.toString()
        })

        buttonLogout.setOnClickListener {
            mViewModel.onAddButtonClicked()
            mPrefsUtil.putRememberUser("logged", false)
            startActivity<SplashActivity>()
            finish()
        }

        val loggedStatus = mPrefsUtil
                .getRememberUser(
                        "logged",
                        false
                )

        longToast(loggedStatus.toString())

    }

    private fun injectMain() {
        SomeApp
                .mInstance
                .mAppComponent
                .inject(this)

    }
}