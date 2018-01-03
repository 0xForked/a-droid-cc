package id.asmith.someappclean.ui.auth

import id.asmith.someappclean.ui.base.BaseViewModel


/**
 * Created by Agus Adhi Sumitro on 01/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class AuthViewModel : BaseViewModel<AuthNavigation>() {

    fun fragmentTransition(UserData: Boolean){

        if (UserData)
            replaceWithSignin()
        else
            replaceWithLock()

    }

    private fun replaceWithLock() = getNavigator()?.replaceWithLockFragment()

    fun replaceWithSignin() = getNavigator()?.replaceWithSigninFragment()

    fun replaceWithSignup()= getNavigator()?.replaceWithSignupFragment()

    fun replaceWithForgot() = getNavigator()?.replaceWithForgotFragment()

    private fun rememberMe() = getNavigator()?.rememberUser()

    fun toast(text: String) = getNavigator()?.customToast(text)

    fun dialog(title: String, message: String) = getNavigator()?.customDialog(title, message)

    /**
     * @name: Fragment Auth Main Function
     * @description:
     * Login function
     * Register function
     * Forgot password function
     * User Detail function (load and save just general data)
     * @param: {full name, email, phone, password}
     */

    fun onLoginButtonPressed(email: String, password: String) {
        toast("Login with $email and $password")
    }

    fun onRegisterButtonPressed(){
        toast("Register")
    }

    fun onForgotSubmitButtonPressed(){
        toast("Forgot")
    }

    private fun getUserDataRemotely(userId: String, userToken: String) {

    }

    private fun putUserDataLocally(){

    }



}