package com.josuearevalodev.base_android.rxdisposablemanager

import io.reactivex.disposables.CompositeDisposable

class RxDisposableManagerImpl : RxDisposableManager {

    override val composite by lazy { CompositeDisposable() }
}
