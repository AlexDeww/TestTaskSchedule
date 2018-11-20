package allexdew.com.testtaskschedule.di

import allexdew.com.testtaskschedule.di.module.ApiModule
import allexdew.com.testtaskschedule.di.module.AppModule
import allexdew.com.testtaskschedule.di.module.DatabaseModule
import allexdew.com.testtaskschedule.di.module.RepositoryModule
import allexdew.com.testtaskschedule.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ApiModule::class,
    DatabaseModule::class,
    RepositoryModule::class
])
interface AppComponents {

    fun inject(mainPresenter: MainPresenter)

}