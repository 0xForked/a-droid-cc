package id.asmith.someappclean.di.module

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import id.asmith.someappclean.di.ViewModelProviderFactory


/**
 * Created by Agus Adhi Sumitro on 02/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

@Module
internal abstract class UIModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}