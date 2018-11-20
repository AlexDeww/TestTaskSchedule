package allexdew.com.testtaskschedule.app.interactor.schedule

import allexdew.com.testtaskschedule.app.entity.ScheduleItem
import allexdew.com.testtaskschedule.app.interactor.UseCase
import allexdew.com.testtaskschedule.app.repository.ScheduleRepository
import allexdew.com.testtaskschedule.core.executor.PostExecutionThread
import allexdew.com.testtaskschedule.core.executor.ThreadExecutor
import io.reactivex.Observable
import javax.inject.Inject

class GetSchedule @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<List<ScheduleItem>, Unit>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Unit): Observable<List<ScheduleItem>> = scheduleRepository
        .getSchedule()
}