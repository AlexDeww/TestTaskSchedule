package allexdew.com.testtaskschedule.app.repository

import allexdew.com.testtaskschedule.app.entity.ScheduleItem
import io.reactivex.Observable

interface ScheduleRepository {

    fun getSchedule(): Observable<List<ScheduleItem>>

}