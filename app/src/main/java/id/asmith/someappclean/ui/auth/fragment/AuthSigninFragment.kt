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
import kotlinx.android.synthetic.main.fragment_auth_signin.view.*
import id.asmith.someappclean.utils.ValidationUtil as util

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

        text_signin_forgot.apply {
            paintFlags = (text_signin_forgot.paintFlags or Paint.UNDERLINE_TEXT_FLAG)
            setTypeface(null, Typeface.BOLD)
            setOnClickListener { mViewModel.replaceWithForgot() }
        }

        val captionRegister = "Don't have an account? <b>Sign up</b>"

        @Suppress("DEPRECATION")
        val spannableStringBuilderRegister = SpannableStringBuilder(Html.fromHtml(captionRegister))
        spannableStringBuilderRegister.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) { mViewModel.replaceWithSignup() }
        }, captionRegister.indexOf("Sign up") - 3,
                spannableStringBuilderRegister.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        @Suppress("DEPRECATION")
        spannableStringBuilderRegister.setSpan(
                ForegroundColorSpan(resources.getColor(R.color.colorPrimary)),
                captionRegister.indexOf("Sign up") - 3,
                spannableStringBuilderRegister.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        text_signin_signup.apply {
            text = spannableStringBuilderRegister
            movementMethod = LinkMovementMethod.getInstance()
        }

        button_signin_go.setOnClickListener {

            val email = input_signin_email.text.toString().trim()
            val password = input_signin_password.text.toString().trim()

            if (email.isEmpty()) input_signin_email.error = (getString(R.string.caption_empty_email))

            if (!util().isEmailValid(email)) mViewModel.toast(getString(R.string.caption_valid_email))

            if (password.isEmpty()) input_signin_password.error = (getString(R.string.caption_empty_password))

            if (!email.isEmpty() and !password.isEmpty() and util().isEmailValid(email))
                mViewModel.onLoginButtonPressed(email, password)

        }

    }


}