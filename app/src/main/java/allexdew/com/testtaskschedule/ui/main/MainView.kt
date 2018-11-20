package allexdew.com.testtaskschedule.ui.main

import allexdew.com.testtaskschedule.app.entity.ScheduleItem
import allexdew.com.testtaskschedule.moxy.strategy.AddToEndSingleByTagStateStrategy
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {

    @StateStrategyType(AddToEndSingleByTagStateStrategy::class, tag = "load")
    fun startLoad()
    @StateStrategyType(AddToEndSingleByTagStateStrategy::class, tag = "load")
    fun loadDone()

    fun updateSchedule(list: List<ScheduleItem>)

    @StateStrategyType(SkipStrategy::class)
    fun showLoadError(error: Throwable)

}