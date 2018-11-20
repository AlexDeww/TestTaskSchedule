package allexdew.com.testtaskschedule.app.interactor

import allexdew.com.testtaskschedule.core.executor.PostExecutionThread
import allexdew.com.testtaskschedule.core.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * https://github.com/android10/Android-CleanArchitecture/blob/master/domain/src/main/java/com/fernandocejas/android10/sample/domain/interactor/UseCase.java
 */
abstract class UseCase<T, PARAMS>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {
    private val disposables = CompositeDisposable()

    internal abstract fun buildUseCaseObservable(params: PARAMS): Observable<T>

    open fun execute(params: PARAMS, observer: DisposableObserver<T>) {
        val observable = buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    fun execute(params: PARAMS, onError: ((Throwable) -> Unit)? = null, onComplete: (() -> Unit)? = null,
                onStart: (() -> Unit)? = null, onEnd: (() -> Unit)? = null, onNext: ((T) -> Unit)? = null): Disposable {
        val observer = UseCaseObserver(onNext, onError, onComplete, onStart, onEnd)
        execute(params, observer)
        return observer
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    fun cancel() {
        disposables.clear()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    private class UseCaseObserver<T>(
            private val actionNext: ((T) -> Unit)? = null,
            private val actionError: ((Throwable) -> Unit)? = null,
            private val actionComplete: (() -> Unit)? = null,
            private val actionStart: (() -> Unit)? = null,
            private val actionEnd: (() -> Unit)? = null
    ) : DisposableObserver<T>() {

        override fun onStart() {
            actionStart?.let { it() }
        }

        override fun onNext(t: T) {
            actionNext?.let { it(t) }
        }

        override fun onComplete() {
            actionComplete?.let { it() }
            doEnd()
        }

        override fun onError(e: Throwable) {
            actionError?.let { it(e) }
            doEnd()
        }

        private fun doEnd() {
            try {
                actionEnd?.let { it() }
            } catch (e: Throwable) {}
        }

    }
}