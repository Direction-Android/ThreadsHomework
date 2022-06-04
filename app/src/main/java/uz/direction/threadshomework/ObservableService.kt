package uz.direction.threadshomework

import android.os.SystemClock
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.random.Random

class ObservableService {
    fun getObservable(): Observable<Int> = Observable.create<Int?> { emit ->
        for (i in 0 until randomNumberGenerator()) {
            SystemClock.sleep(randomNumberGenerator().toLong())
            emit.onNext(i)

        }
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    private fun randomNumberGenerator(): Int {
        return Random.nextInt(0, 100)
    }
}