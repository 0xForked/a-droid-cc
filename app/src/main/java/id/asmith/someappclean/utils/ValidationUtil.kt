package id.asmith.someappclean.utils

import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 * Created by Agus Adhi Sumitro on 23/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class ValidationUtil {

    //Cek valid email
    fun isEmailValid(email: String): Boolean {
        val pattern: Pattern = Pattern.compile(AppConstants.EMAIL_PATTERN)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    //cek phone validation
    fun isPhoneValid(phone: String): Boolean {
        val pattern: Pattern = Pattern.compile(AppConstants.PHONE_PATTERN)
        val matcher: Matcher = pattern.matcher(phone)
        return matcher.matches()
    }

    //cek username validation
    fun isUsernameValid (username: String): Boolean {
        val pattern: Pattern = Pattern.compile(AppConstants.USERNAME_PATTERN)
        val matcher: Matcher = pattern.matcher(username)
        return matcher.matches()
    }

}