package allexdew.com.testtaskschedule.core.presenter

import allexdew.com.testtaskschedule.ScheduleApplication
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : MvpView> : MvpPresenter<V>() {

    protected val graph get() = ScheduleApplication.graph

    private val disposableList = HashMap<String, Disposable>()

    override fun onDestroy() {
        disposableList.values.forEach { it.dispose() }
        super.onDestroy()
    }

    protected fun Disposable.disposeOnDestroy(tag: String) {
        disposableList.put(tag, this)?.dispose()
    }
}