package allexdew.com.testtaskschedule.di.module

import allexdew.com.testtaskschedule.app.executor.JobExecutor
import allexdew.com.testtaskschedule.app.executor.UIExecutor
import allexdew.com.testtaskschedule.core.executor.PostExecutionThread
import allexdew.com.testtaskschedule.core.executor.ThreadExecutor
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
    private val appContext: Context
) {

    @Provides
    fun providerAppContext(): Context = appContext

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @Singleton
    fun providePostExecutionThread(uiExecutor: UIExecutor): PostExecutionThread = uiExecutor

}