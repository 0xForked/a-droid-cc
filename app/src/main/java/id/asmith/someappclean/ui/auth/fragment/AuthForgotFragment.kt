package id.asmith.someappclean.ui.auth.fragment

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.asmith.someappclean.R
import id.asmith.someappclean.ui.auth.AuthViewModel
import kotlinx.android.synthetic.main.fragment_auth_forgot_password.*
import org.jetbrains.anko.longToast
import id.asmith.someappclean.utils.ValidationUtil as utils

/**
 * Created by Agus Adhi Sumitro on 01/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class AuthForgotFragment : Fragment() {

    private val mViewModel: AuthViewModel by lazy {
        ViewModelProviders.of(activity!!).get(AuthViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth_forgot_password, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        text_forgot_back.paintFlags = (text_forgot_back.paintFlags or
                Paint.UNDERLINE_TEXT_FLAG)
        text_forgot_back.setTypeface(null, Typeface.BOLD)
        text_forgot_back.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        text_forgot_submit.paintFlags = (text_forgot_submit.paintFlags or
                Paint.UNDERLINE_TEXT_FLAG)
        text_forgot_submit.setTypeface(null, Typeface.BOLD)
        text_forgot_submit.setOnClickListener {
            val forgotEmail =  input_forgot_email.text.toString().trim()

            if (forgotEmail.isEmpty())
                activity?.longToast( getString(R.string.caption_empty_email))
            if (!utils().isEmailValid(forgotEmail))
                activity?.longToast( getString(R.string.caption_valid_email))
            if (!forgotEmail.isEmpty() and utils().isEmailValid(forgotEmail))
                mViewModel.onForgotSubmitButtonPressed(forgotEmail)
        }

    }

}