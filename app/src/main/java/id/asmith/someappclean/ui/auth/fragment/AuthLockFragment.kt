package id.asmith.someappclean.ui.auth.fragment

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import id.asmith.someappclean.R
import id.asmith.someappclean.ui.auth.AuthViewModel
import kotlinx.android.synthetic.main.fragment_auth_lock_user.*

/**
 * Created by Agus Adhi Sumitro on 01/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class AuthLockFragment : Fragment(), PopupMenu.OnMenuItemClickListener{

    private val mViewModel: AuthViewModel by lazy {
        ViewModelProviders.of(activity!!).get(AuthViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_auth_lock_user, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        text_lock_forgot.paintFlags = (text_lock_forgot.paintFlags or
                Paint.UNDERLINE_TEXT_FLAG)
        text_lock_forgot.setTypeface(null, Typeface.BOLD)
        text_lock_forgot.setOnClickListener {
            mViewModel.replaceWithForgot()
        }

        text_lock_singin.paintFlags = (text_lock_forgot.paintFlags or
                Paint.UNDERLINE_TEXT_FLAG)
        text_lock_singin.setTypeface(null, Typeface.BOLD)
        text_lock_singin.setOnClickListener {
            mViewModel.replaceWithSignin()
        }

        button_lock_menu.setOnClickListener { view ->
            showMenu(view)
        }

        button_lock_go.setOnClickListener {
            val lockEmail =  text_lock_email.text.toString().trim()
            val lockPassword = input_lock_password.text.toString().trim()

            if (lockPassword.isEmpty()) mViewModel.toast(getString(R.string.caption_empty_password))

            if (!lockPassword.isEmpty())
                mViewModel.onLoginButtonPressed(lockEmail, lockPassword)

        }

    }


    private fun showMenu(view: View) {
        val popup = PopupMenu(activity, view)
        popup.setOnMenuItemClickListener(this@AuthLockFragment)
        popup.inflate(R.menu.menu_lock)
        popup.show()
    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {

        when (item?.itemId) {

            R.id.action_delete ->
                mViewModel.dialog(
                        "Remove account",
                        "Are you sure?"
                )

        }

        return true
    }

}