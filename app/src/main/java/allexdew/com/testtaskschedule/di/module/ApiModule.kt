package allexdew.com.testtaskschedule.di.module

import allexdew.com.testtaskschedule.BuildConfig
import allexdew.com.testtaskschedule.api.ScheduleApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    private val apiBuilder = Retrofit.Builder()
        .baseUrl(BuildConfig.SHEDULE_API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideScheduleApi(): ScheduleApi = apiBuilder.create(ScheduleApi::class.java)

}