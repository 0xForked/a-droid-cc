package id.asmith.someappclean.ui.auth

import android.app.ProgressDialog
import android.widget.Toast


/**
 * Created by Agus Adhi Sumitro on 02/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
interface AuthNavigation {

    fun rememberUser(): Boolean

    fun customToast(text: String): Toast

    fun customProgressDialog(): ProgressDialog

    fun startMainActivity()

    fun replaceWithLockFragment()

    fun replaceWithSigninFragment()

    fun replaceWithSignupFragment()

    fun replaceWithForgotFragment()

}