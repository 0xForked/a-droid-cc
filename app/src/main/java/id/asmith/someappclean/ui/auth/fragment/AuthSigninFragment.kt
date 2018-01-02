package id.asmith.someappclean.ui.auth.fragment

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.asmith.someappclean.R
import id.asmith.someappclean.SomeApp
import id.asmith.someappclean.ui.splash.SplashActivity
import id.asmith.someappclean.utils.PreferencesUtil
import kotlinx.android.synthetic.main.fragment_auth_signin.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import javax.inject.Inject


/**
 * Created by Agus Adhi Sumitro on 01/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class AuthSigninFragment : Fragment() {

    @Inject
    lateinit var mPrefsUtil: PreferencesUtil

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth_signin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        text_signin_forgot.paintFlags = (text_signin_forgot.paintFlags or
                Paint.UNDERLINE_TEXT_FLAG)
        text_signin_forgot.setTypeface(null, Typeface.BOLD)
        text_signin_forgot.setOnClickListener {
            replaceForgot()
        }

        //Register caption
        val captionRegister = "Don't have an account? <b>Sign up</b>"

        @Suppress("DEPRECATION")
        val spannableStringBuilderRegister = SpannableStringBuilder(Html.fromHtml(captionRegister))
        spannableStringBuilderRegister.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                replaceSignup()
            }
        }, captionRegister.indexOf("Sign up") - 3,
                spannableStringBuilderRegister.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        @Suppress("DEPRECATION")
        spannableStringBuilderRegister.setSpan(
                ForegroundColorSpan(resources.getColor(R.color.colorPrimary)),
                captionRegister.indexOf("Sign up") - 3,
                spannableStringBuilderRegister.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        text_signin_signup.text = spannableStringBuilderRegister
        text_signin_signup.movementMethod = LinkMovementMethod.getInstance()

        injectSignin()

        button_signin_go.setOnClickListener {
            mPrefsUtil.putRememberUser("logged", true)
            activity!!.startActivity<SplashActivity>()
            activity!!.finish()
        }

        val loggedStatus = mPrefsUtil
                .getRememberUser(
                        "logged",
                        false
                )

        activity!!.longToast(loggedStatus.toString())
    }


    private fun injectSignin() {
       SomeApp
                .mInstance
                .mAppComponent
                .inject(this)

    }

    private fun replaceForgot() {
        fragmentManager!!
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(R.id.fragment_container, AuthForgotFragment())
                .addToBackStack(null)
                .commit()
    }

    private fun replaceSignup() {
        fragmentManager!!
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(R.id.fragment_container, AuthSignupFragment())
                .addToBackStack(null)
                .commit()
    }
}