package id.asmith.someappclean.ui.base

import android.arch.lifecycle.ViewModel


/**
 * Created by Agus Adhi Sumitro on 02/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
abstract class BaseViewModel<Navigator> : ViewModel() {

    private var mNavigator: Navigator? = null

    fun setNavigator(navigator: Navigator) {
        this.mNavigator = navigator
    }

    fun getNavigator(): Navigator? = mNavigator

}