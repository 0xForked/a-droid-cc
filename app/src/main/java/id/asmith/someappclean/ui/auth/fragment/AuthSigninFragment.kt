package id.asmith.someappclean.ui.auth.fragment

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
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
import id.asmith.someappclean.ui.auth.AuthViewModel
import kotlinx.android.synthetic.main.fragment_auth_signin.*


/**
 * Created by Agus Adhi Sumitro on 01/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class AuthSigninFragment : Fragment() {

    private val mViewModel: AuthViewModel by lazy {
        ViewModelProviders.of(activity!!).get(AuthViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_auth_signin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        text_signin_forgot.paintFlags = (text_signin_forgot.paintFlags or
                Paint.UNDERLINE_TEXT_FLAG)
        text_signin_forgot.setTypeface(null, Typeface.BOLD)
        text_signin_forgot.setOnClickListener {
            mViewModel.replaceWithForgot()
        }

        val captionRegister = "Don't have an account? <b>Sign up</b>"

        @Suppress("DEPRECATION")
        val spannableStringBuilderRegister = SpannableStringBuilder(Html.fromHtml(captionRegister))
        spannableStringBuilderRegister.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                mViewModel.replaceWithSignup()
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

        button_signin_go.setOnClickListener {
            mViewModel.onLoginButtonPressed()
        }

    }


}