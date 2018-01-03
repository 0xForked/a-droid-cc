package id.asmith.someappclean.data.remote

import io.reactivex.Observable
import okhttp3.ResponseBody
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
    ): Observable<ResponseBody>

    @FormUrlEncoded
    @POST("auth/signup")
    fun signUp (
            @Field("full_name")reg_name: String,
            @Field("username")reg_username: String,
            @Field("phone")reg_phone: String,
            @Field("email")reg_email: String,
            @Field("password")reg_password: String
    ): Observable<ResponseBody>

    @FormUrlEncoded
    @POST("user/password/forgot")
    fun forgotPassword (
            @Field("email") email: String
    ): Observable<ResponseBody>

    @FormUrlEncoded
    @POST("user/password/change")
    fun changePassword (
            @Field("id") uid: String,
            @Field("password_old")password_old: String,
            @Field("password_new")password_new: String
    ): Observable<ResponseBody>

    @FormUrlEncoded
    @POST("user/detail")
    fun userDetail (
            @Field("uid") uid: String,
            @Field("token") token: String
    ): Observable<ResponseBody>
}