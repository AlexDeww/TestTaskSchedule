package allexdew.com.testtaskschedule.ui.main

import allexdew.com.testtaskschedule.app.interactor.schedule.GetSchedule
import allexdew.com.testtaskschedule.core.presenter.BasePresenter
import com.arellomobile.mvp.InjectViewState
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class MainPresenter : BasePresenter<MainView>() {

    @Inject
    lateinit var getSchedule: GetSchedule

    init {
        graph.inject(this)
    }

    override fun onDestroy() {
        getSchedule.dispose()
        super.onDestroy()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        Completable.timer(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { loadData() }
            .disposeOnDestroy("load")
    }

    fun loadData() {
        getSchedule.cancel()
        getSchedule.execute(Unit,
            onNext = viewState::updateSchedule,
            onError = viewState::showLoadError,
            onStart = viewState::startLoad,
            onEnd = viewState::loadDone
        )
    }
}