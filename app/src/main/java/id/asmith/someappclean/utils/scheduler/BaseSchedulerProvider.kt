package id.asmith.someappclean.utils.scheduler

import io.reactivex.Scheduler


/**
 * Created by Agus Adhi Sumitro on 27/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
interface BaseSchedulerProvider {

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun io(): Scheduler

    fun multi(): Scheduler

}