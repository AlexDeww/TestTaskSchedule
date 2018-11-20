package allexdew.com.testtaskschedule.app.repository.source.database.dao

import allexdew.com.testtaskschedule.app.entity.ScheduleItem
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import io.reactivex.Maybe

@Dao
abstract class ScheduleDao {

    @Transaction
    open fun updateAll(newList: List<ScheduleItem>) {
        deleteAll()
        newList.forEach { it.id = insert(it) }
    }

    @Insert
    abstract fun insert(item: ScheduleItem): Long

    @Query("DELETE FROM schedule")
    abstract fun deleteAll()

    @Query("SELECT * FROM schedule")
    abstract fun getSchedule(): Maybe<List<ScheduleItem>>

}