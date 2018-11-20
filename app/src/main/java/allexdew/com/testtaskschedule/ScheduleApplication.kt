package allexdew.com.testtaskschedule

import allexdew.com.testtaskschedule.app.repository.source.database.AppDatabase
import allexdew.com.testtaskschedule.di.AppComponents
import allexdew.com.testtaskschedule.di.DaggerAppComponents
import allexdew.com.testtaskschedule.di.module.ApiModule
import allexdew.com.testtaskschedule.di.module.AppModule
import allexdew.com.testtaskschedule.di.module.DatabaseModule
import allexdew.com.testtaskschedule.di.module.RepositoryModule
import android.app.Application
import android.arch.persistence.room.Room
import android.util.Log
import io.reactivex.plugins.RxJavaPlugins

class ScheduleApplication : Application() {

    companion object {
        private const val TAG_RX_ERROR = "RX_ERROR"

        lateinit var graph: AppComponents
            private set
    }

    private lateinit var appDataBase: AppDatabase

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler { Log.e(TAG_RX_ERROR, "", it) }

        appDataBase = buildDatabase()
        graph = buildComponent()
    }

    private fun buildDatabase() = Room
        .databaseBuilder(this, AppDatabase::class.java, "schedule_db")
        .build()

    @Suppress("DEPRECATION")
    private fun buildComponent(): AppComponents = DaggerAppComponents.builder()
        .appModule(AppModule(this))
        .apiModule(ApiModule())
        .databaseModule(DatabaseModule(appDataBase))
        .repositoryModule(RepositoryModule())
        .build()

}