package id.asmith.someappclean.utils

import android.content.SharedPreferences
import javax.inject.Inject


/**
 * Created by Agus Adhi Sumitro on 31/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class PreferencesUtil @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun putRememberUser(key: String, value: Boolean): Boolean {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        return editor.commit()
    }

    fun getRememberUser(key: String, defaultValue: Boolean): Boolean =
            sharedPreferences.getBoolean(key, defaultValue)

}