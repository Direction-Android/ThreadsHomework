package uz.direction.threadshomework

import android.os.Bundle
import android.os.Message
import android.os.SystemClock
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class Service {

    fun getObserver(): Observable<Int> = Observable.create<Int> { emitter ->
        val freq = (50..150).random().toLong()
        val finish = (1..100).random()
        for (i in 0 until finish) {
            SystemClock.sleep(freq)
            emitter.onNext(i)
        }
    }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}