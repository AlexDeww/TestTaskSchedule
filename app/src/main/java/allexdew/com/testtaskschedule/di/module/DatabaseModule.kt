package allexdew.com.testtaskschedule.di.module

import allexdew.com.testtaskschedule.app.repository.source.database.AppDatabase
import allexdew.com.testtaskschedule.app.repository.source.database.dao.ScheduleDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(
    private val appDatabase: AppDatabase
) {

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase = appDatabase

    @Provides
    fun provideScheduleDao(): ScheduleDao = appDatabase.getScheduleDao()

}