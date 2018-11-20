package allexdew.com.testtaskschedule.app.repository.impl

import allexdew.com.testtaskschedule.api.ScheduleApi
import allexdew.com.testtaskschedule.app.entity.ScheduleItem
import allexdew.com.testtaskschedule.app.repository.ScheduleRepository
import allexdew.com.testtaskschedule.app.repository.source.database.dao.ScheduleDao
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleDao: ScheduleDao,
    private val scheduleApi: ScheduleApi
) : ScheduleRepository {

    override fun getSchedule(): Observable<List<ScheduleItem>> = Observable.concat(
        getFromDb(),
        getFromApi()
    )

    private fun getFromApi(): Observable<List<ScheduleItem>> = scheduleApi
        .getSchedule()
        .map { list -> list.map { ScheduleItem(0, it.name, it.startTime, it.endTime, it.teacher, it.place, it.description, it.weekDay) } }
        .doOnSuccess { scheduleDao.updateAll(it) }
        .toObservable()

    private fun getFromDb(): Observable<List<ScheduleItem>> = scheduleDao
        .getSchedule()
        .map {
            if (it.isEmpty()) {
                listOf(
                    ScheduleItem(1, "Test1"),
                    ScheduleItem(2, "Test2"),
                    ScheduleItem(3, "Test3"),
                    ScheduleItem(4, "Test4"),
                    ScheduleItem(5, "Test5"),
                    ScheduleItem(6, "Test6"),
                    ScheduleItem(7, "Test7"),
                    ScheduleItem(8, "Test8"),
                    ScheduleItem(9, "Test9"),
                    ScheduleItem(10, "Test10"),
                    ScheduleItem(11, "Test11"),
                    ScheduleItem(12, "Test12"),
                    ScheduleItem(13, "Test13")
                )
            } else {
                it
            }
        }
        .toObservable()
}