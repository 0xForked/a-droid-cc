package id.asmith.someappclean.ui.auth

import android.util.Log
import id.asmith.someappclean.data.remote.ApiService
import id.asmith.someappclean.ui.base.BaseViewModel
import id.asmith.someappclean.utils.scheduler.AppSchedulerProvider
import org.json.JSONObject
import java.util.*


/**
 * Created by Agus Adhi Sumitro on 01/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class AuthViewModel : BaseViewModel<AuthNavigation>() {

    private var mApiIService: ApiService? = null

    fun setApiIService(apiService: ApiService) {
        this.mApiIService = apiService
    }

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

    private fun progressDialog () = getNavigator()?.customProgressDialog()


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
        val dialog = progressDialog()!!
        dialog.setCancelable(false)
        dialog.show()

        mApiIService!!.signIn(email, password)
                .subscribeOn(AppSchedulerProvider().multi())
                .observeOn(AppSchedulerProvider().ui())
                .subscribe({ onSuccessResponse ->

                    val mResult = JSONObject(onSuccessResponse.string())
                    val mUserData = mResult.getJSONObject("user")
                    val userId = mUserData.getString("uid")
                    val userToken = mUserData.getString("token")
                    val userStatus = mUserData.getString("isActive").toInt()

                    if (userStatus != 0) {

                        Log.d("Login", "uid: $userId, token: $userToken")

                        getUserDataRemotely(userId, userToken)

                    } else {

                        Log.d("Login", "Success but user is inActive")

                    }

                    dialog.dismiss()

                }, { onErrorResponse ->

                    dialog.dismiss()
                    onErrorResponse.printStackTrace()

                })

    }

    fun onRegisterButtonPressed(regName: String, regUsername: String, regPhone: String,
                                regEmail: String, regPassword: String){
        val dialog = progressDialog()!!
        dialog.setCancelable(false)
        dialog.show()

        mApiIService!!.signUp(regName, regUsername, regPhone, regEmail, regPassword)
                .subscribeOn(AppSchedulerProvider().multi())
                .observeOn(AppSchedulerProvider().ui())
                .subscribe({ onSuccessResponse ->

                    val mResult = JSONObject(onSuccessResponse.string())
                    val mUserData = mResult.getJSONObject("user")
                    val userId = mUserData.getString("uid")
                    val userToken = mUserData.getString("token")

                    Log.d("Login", "uid: $userId, token: $userToken")

                    getUserDataRemotely(userId, userToken)

                    dialog.dismiss()

                }, { onErrorResponse ->

                    dialog.dismiss()
                    onErrorResponse.printStackTrace()

                })

    }

    fun onForgotSubmitButtonPressed(email: String){
        val dialog = progressDialog()!!
        dialog.setCancelable(false)
        dialog.show()

        mApiIService!!.forgotPassword(email)
                .subscribeOn(AppSchedulerProvider().multi())
                .observeOn(AppSchedulerProvider().ui())
                .subscribe({ onSuccessResponse ->

                    val mResult = JSONObject(onSuccessResponse.string())
                    val status = mResult.getString("status")
                    val message = mResult.getString("message")

                    Log.d("LOGIN RESET", "SUCCESS $status $message")

                    dialog.dismiss()

                }, { onErrorResponse ->

                    dialog.dismiss()
                    onErrorResponse.printStackTrace()

                })

    }

    private fun getUserDataRemotely(uid: String, token: String) {

        val dialog = progressDialog()!!
        dialog.setCancelable(false)
        dialog.show()

        mApiIService!!.userDetail(uid, token)
                .subscribeOn(AppSchedulerProvider().multi())
                .observeOn(AppSchedulerProvider().ui())
                .subscribe({ onSuccessResponse ->

                    val mResult = JSONObject(onSuccessResponse.string())
                    val mUserData = mResult.getJSONObject("user")
                    val userId = mUserData.getString("uid")
                    val userInitial = mUserData.getString("username")
                    val userFullName = mUserData.getString("name")
                    val userEmail = mUserData.getString("email")
                    val userPhone = mUserData.getString("phone")
                    val userToken = mUserData.getString("token")
                    val createdOn = Calendar.getInstance().time
                    val updatedOn = Calendar.getInstance().time

                    Log.d("LOGIN DETAIL","$userId, $userInitial, $userFullName, " +
                            "$userEmail, $userPhone, $userToken, $createdOn, $updatedOn" )
                    toast(mResult.toString())

                    dialog.dismiss()

                }, { onErrorResponse ->

                    dialog.dismiss()
                    onErrorResponse.printStackTrace()

                })

    }

    private fun putUserDataLocally(){

    }

}