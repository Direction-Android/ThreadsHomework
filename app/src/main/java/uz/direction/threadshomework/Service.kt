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

    fun getObserver(): Observable<Progress> = Observable.create<Progress> { emitter ->
        val freq = 150
        val finish = (1..100).random()
        val progress = Progress(0, finish)

        for (i in 0 until finish) {
            SystemClock.sleep(freq.toLong())
            progress.progress = i
            emitter.onNext(progress)
        }
    }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}