package id.asmith.someappclean.ui.auth.fragment

import android.arch.lifecycle.ViewModelProviders
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
import kotlinx.android.synthetic.main.fragment_auth_signup.*
import id.asmith.someappclean.utils.ValidationUtil as util

/**
 * Created by Agus Adhi Sumitro on 01/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class AuthSignupFragment : Fragment() {

    private val mViewModel: AuthViewModel by lazy {
        ViewModelProviders.of(activity!!).get(AuthViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_auth_signup, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        image_signup_userPic.setOnClickListener {
            mViewModel.toast("Add user pic")
        }

        val captionLogin = "Already a member? <b>Sign in</b>"

        @Suppress("DEPRECATION")
        val spannableStringBuilderLogin = SpannableStringBuilder(Html.fromHtml(captionLogin))
        spannableStringBuilderLogin.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                fragmentManager?.popBackStack()
            }
        }, captionLogin.indexOf("Sign in") - 3,
                spannableStringBuilderLogin.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        @Suppress("DEPRECATION")
        spannableStringBuilderLogin.setSpan(
                ForegroundColorSpan(resources.getColor(R.color.colorPrimary)),
                captionLogin.indexOf("Sign in") - 3,
                spannableStringBuilderLogin.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        text_signup_signin.text = spannableStringBuilderLogin
        text_signup_signin.movementMethod = LinkMovementMethod.getInstance()

        button_signup_go.setOnClickListener {

            val regName = input_signup_fullName.text.toString().trim()
            val regUsername = "Asmith_Users" //actually we not need this
            val regEmail = input_signup_email.text.toString().trim()
            val regPhone = input_signup_phone.text.toString().trim()
            val regPassword = input_signup_password.text.toString().trim()

            if (regName.isEmpty()) mViewModel.toast(getString(R.string.caption_empty_name))
            if (regEmail.isEmpty()) mViewModel.toast(getString(R.string.caption_empty_email))
            if (regPhone.isEmpty()) mViewModel.toast(getString(R.string.caption_empty_phone))
            if (regPassword.isEmpty()) mViewModel.toast(getString(R.string.caption_empty_password))

            if (!util().isEmailValid(regEmail)) mViewModel.toast(getString(R.string.caption_valid_email))
            if (!util().isPhoneValid(regPhone)) mViewModel.toast(getString(R.string.caption_valid_phone))

            if (!regName.isEmpty() and !regEmail.isEmpty() and util().isEmailValid(regEmail)
                    and !regPhone.isEmpty() and util().isPhoneValid(regPhone)
                    and !regPassword.isEmpty()) {

                mViewModel.onRegisterButtonPressed(regName, regUsername,
                        regPhone, regEmail, regPassword)

            }

        }

    }

}