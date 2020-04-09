package com.josuearevalodev.base_android.rxdisposablemanager

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

interface RxDisposableManager {

    val composite: CompositeDisposable

    val mainThread: Scheduler
        get() = AndroidSchedulers.mainThread()

    val ioThread: Scheduler
        get() = Schedulers.io()
}
