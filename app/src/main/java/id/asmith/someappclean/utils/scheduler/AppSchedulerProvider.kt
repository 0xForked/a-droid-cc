package id.asmith.someappclean.utils.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by Agus Adhi Sumitro on 27/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class AppSchedulerProvider : BaseSchedulerProvider {

    override fun computation(): Scheduler = Schedulers.computation()

    override fun multi(): Scheduler = Schedulers.newThread()

    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

}