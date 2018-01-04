package id.asmith.someappclean.ui.auth

import android.arch.lifecycle.ViewModel
import id.asmith.someappclean.data.local.LocalDataHandler
import id.asmith.someappclean.data.model.UserModel
import id.asmith.someappclean.data.remote.ApiService
import id.asmith.someappclean.utils.AppConstants.USER_LOG_STATUS
import id.asmith.someappclean.utils.PreferencesUtil
import id.asmith.someappclean.utils.scheduler.AppSchedulerProvider
import org.json.JSONObject
import retrofit2.HttpException
import java.util.*


/**
 * Created by Agus Adhi Sumitro on 01/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class AuthViewModel : ViewModel() {

    private var mApiIService: ApiService? = null

    fun setApiService(apiService: ApiService) {
        this.mApiIService = apiService
    }

    private var mNavigator: AuthNavigation? = null

    fun setNavigator(navigator: AuthNavigation) {
        this.mNavigator = navigator
    }

    private var mPrefs: PreferencesUtil? = null

    fun setPrefs(sharedPreferences: PreferencesUtil) {
        this.mPrefs = sharedPreferences
    }

    private var mDatabase: LocalDataHandler? = null

    fun setDatabaseHelper(dbHelper: LocalDataHandler) {
        this.mDatabase = dbHelper
    }

    fun fragmentTransition(UserData: Boolean){

        if (!UserData)
            replaceWithSignin()
        else
            replaceWithLock()

    }

    private fun replaceWithLock() = mNavigator?.replaceWithLockFragment()

    fun replaceWithSignin() = mNavigator?.replaceWithSigninFragment()

    fun replaceWithSignup()= mNavigator?.replaceWithSignupFragment()

    fun replaceWithForgot() = mNavigator?.replaceWithForgotFragment()

    fun toast(msg: String) = mNavigator?.customToast(msg)

    private fun progressDialog () = mNavigator?.customProgressDialog()


    /**
     * @name: Fragment Auth Main Function
     * @description:
         * Login function
         * Register function
         * Forgot password function
         * User Detail function (load and save [general data])
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

                        getUserDataRemotely(userId, userToken)

                    } else {

                        toast("Your account is inactive")

                    }

                    dialog.dismiss()

                }, { onErrorResponse ->

                    dialog.dismiss()
                    onErrorResponse.printStackTrace()
                    if (onErrorResponse is HttpException) {
                        val mResult = JSONObject(onErrorResponse.response()
                                .errorBody()?.string())
                        val message = mResult.getString("message")
                        toast(message)
                    }

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

                    getUserDataRemotely(userId, userToken)

                    dialog.dismiss()

                }, { onErrorResponse ->

                    dialog.dismiss()
                    onErrorResponse.printStackTrace()
                    if (onErrorResponse is HttpException) {
                        val mResult = JSONObject(onErrorResponse.response()
                                .errorBody()?.string())
                        toast(mResult.toString())
                    }

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
                    val message = mResult.getString("message")

                    toast(message)

                    dialog.dismiss()

                }, { onErrorResponse ->

                    dialog.dismiss()
                    onErrorResponse.printStackTrace()
                    if (onErrorResponse is HttpException) {
                        val mResult = JSONObject(onErrorResponse.response()
                                .errorBody()?.string())
                        val message = mResult.getString("message")
                        toast(message)
                    }

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
                    val userName = mUserData.getString("name")
                    val userEmail = mUserData.getString("email")
                    val userPhone = mUserData.getString("phone")
                    val userToken = mUserData.getString("token")
                    val createdOn = Calendar.getInstance().time
                    val updatedOn = Calendar.getInstance().time

                    putUserDataLocally(userId, userName, userEmail,
                    userPhone, userToken, createdOn.toString(), updatedOn.toString())

                    dialog.dismiss()

                }, { onErrorResponse ->

                    dialog.dismiss()
                    onErrorResponse.printStackTrace()

                })

    }

    private fun putUserDataLocally(userId: String, userFullName: String, userEmail: String,
                                   userPhone: String, userToken: String, createdOn: String,
                                   updatedOn: String){

        mDatabase!!.addUserData(UserModel(userId, userFullName,
                userEmail, userPhone, userToken,
                createdOn, updatedOn))

        mPrefs!!.putRememberUser(USER_LOG_STATUS, true)

        mNavigator!!.startMainActivity()

    }

    fun deleteUserDataLocal(){

        mDatabase!!.deleteTableUser()
        mNavigator!!.startThisActivity()

    }

}