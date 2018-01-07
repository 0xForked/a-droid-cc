package id.asmith.someappclean.ui.main

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.asmith.someappclean.R
import id.asmith.someappclean.SomeApp
import id.asmith.someappclean.data.local.LocalDataHandler
import id.asmith.someappclean.ui.auth.AuthActivity
import id.asmith.someappclean.utils.AppConstants
import id.asmith.someappclean.utils.PreferencesUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
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

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectMain()

        //access user local data
        val userData = LocalDataHandler(this).getUserData()
        text_name.text = userData["name"]
        text_email.text = userData["email"]
        text_token.text = "Token : " +userData["token"]

        mViewModel.getCounter().observe(this, Observer {
            text_number.text = it.toString()
        })

        button_main_plus.setOnClickListener {
            mViewModel.onPlusButtonClicked()

        }

        button_main_min.setOnClickListener {
            mViewModel.onMinButtonClicked()
        }

        button_main_password.setOnClickListener {
            toast("Change password")
        }

        button_main_logout.setOnClickListener {

            alert("Are you sure?", "Logout") {
                positiveButton("LOGOUT") {
                    mPrefsUtil.putRememberUser(AppConstants.USER_LOG_STATUS, false)
                    startActivity<AuthActivity>()
                    finish()
                }
                negativeButton("CANCEL"){}
            }.show().setCancelable(false)

        }

    }

    private fun injectMain() {
        SomeApp
                .mInstance
                .mAppComponent
                .inject(this)

    }
}