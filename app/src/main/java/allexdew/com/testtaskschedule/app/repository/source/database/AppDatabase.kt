package allexdew.com.testtaskschedule.app.repository.source.database

import allexdew.com.testtaskschedule.app.entity.ScheduleItem
import allexdew.com.testtaskschedule.app.repository.source.database.dao.ScheduleDao
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(version = 1, exportSchema = false, entities = [
    ScheduleItem::class
])
abstract class AppDatabase : RoomDatabase() {

    abstract fun getScheduleDao(): ScheduleDao

}