package allexdew.com.testtaskschedule.di.module

import allexdew.com.testtaskschedule.app.repository.ScheduleRepository
import allexdew.com.testtaskschedule.app.repository.impl.ScheduleRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideScheduleRepository(impl: ScheduleRepositoryImpl): ScheduleRepository = impl

}