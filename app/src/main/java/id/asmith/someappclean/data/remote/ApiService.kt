package id.asmith.someappclean.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Created by Agus Adhi Sumitro on 27/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

interface ApiService {

    @FormUrlEncoded
    @POST("auth/signin")
    fun signIn (
            @Field("email") email: String,
            @Field("password") password: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("auth/signup")
    fun signUp (
            @Field("full_name")reg_name: String,
            @Field("username")reg_username: String,
            @Field("phone")reg_phone: String,
            @Field("email")reg_email: String,
            @Field("password")reg_password: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("user/password/forgot")
    fun forgotPassword (
            @Field("email") email: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("user/password/change")
    fun changePassword (
            @Field("id") user_id: String,
            @Field("password_old")password_old: String,
            @Field("password_new")password_new: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("detail")
    fun userDetail (
            @Field("uid") user_id: String,
            @Field("token") token: String
    ): Call<ResponseBody>
}