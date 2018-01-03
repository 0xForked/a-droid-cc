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

    /**
     * @name: Fragment Main Function
     * @description:
         * Login function
         * Register function
         * Forgot password function
         * User Detail function (load and save just general data)
     * @param: {full name, email, phone, password}
    */

    fun onLoginButtonPressed() {
        getNavigator()?.tst()
    }

    fun onRegisterButtonPressed(){
        getNavigator()?.tst()
    }

    fun onForgotSubmitButtonPressed(){
        getNavigator()?.tst()
    }

    private fun getUserDataRemotely() {

    }

    private fun putUserDataLocally(){

    }



}