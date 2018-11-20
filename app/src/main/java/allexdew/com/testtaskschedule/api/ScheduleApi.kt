package allexdew.com.testtaskschedule.api

import allexdew.com.testtaskschedule.api.entity.shedule.ApiScheduleItem
import io.reactivex.Single
import retrofit2.http.GET

interface ScheduleApi {

    @GET("get_group_lessons_v2/1/")
    fun getSchedule(): Single<List<ApiScheduleItem>>

}