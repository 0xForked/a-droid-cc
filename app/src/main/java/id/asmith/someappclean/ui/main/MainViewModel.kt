package id.asmith.someappclean.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


/**
 * Created by Agus Adhi Sumitro on 23/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class  MainViewModel : ViewModel() {

    private val counter = MutableLiveData<Int>()

    init {
        counter.value = 0
    }

    fun onAddButtonClicked() {
        counter.value?.let {
            counter.value = it + 1
        }
    }

    fun getCounter(): LiveData<Int> = counter
}