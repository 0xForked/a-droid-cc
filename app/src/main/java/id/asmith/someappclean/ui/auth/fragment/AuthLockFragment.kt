package id.asmith.someappclean.ui.auth.fragment

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import id.asmith.someappclean.R
import kotlinx.android.synthetic.main.fragment_auth_lock_user.*
import org.jetbrains.anko.*

/**
 * Created by Agus Adhi Sumitro on 01/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class AuthLockFragment : Fragment(), PopupMenu.OnMenuItemClickListener{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth_lock_user, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        text_lock_forgot.paintFlags = (text_lock_forgot.paintFlags or
                Paint.UNDERLINE_TEXT_FLAG)
        text_lock_forgot.setTypeface(null, Typeface.BOLD)
        text_lock_forgot.setOnClickListener {
            replaceForgot()
        }

        text_lock_singin.paintFlags = (text_lock_forgot.paintFlags or
                Paint.UNDERLINE_TEXT_FLAG)
        text_lock_singin.setTypeface(null, Typeface.BOLD)
        text_lock_singin.setOnClickListener {
            replaceSignin()
        }

        button_lock_menu.setOnClickListener { view ->
            showMenu(view)
        }

        button_lock_go.setOnClickListener {
            val lockEmail =  text_lock_email.text.toString().trim()
            val lockPassword = input_lock_password.text.toString().trim()

            if (lockPassword.isEmpty())
                activity?.longToast(getString(R.string.caption_empty_password))

            //if ( !lockPassword.isEmpty())
                //loggedUserIn(lockEmail, lockPassword)
        }

    }

    private fun replaceForgot() {
        fragmentManager!!
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(R.id.fragment_container, AuthForgotFragment())
                .addToBackStack(null)
                .commit()
    }


    private fun replaceSignin() {
        fragmentManager!!
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(R.id.fragment_container, AuthSigninFragment())
                .addToBackStack(null)
                .commit()
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
                activity?.alert("Are you sure? ",
                        "Remove account") {
                    yesButton {
                        activity?.toast("Whoops")
                    }
                    noButton {}
                }?.show()?.setCancelable(false)

        }

        return true
    }
}