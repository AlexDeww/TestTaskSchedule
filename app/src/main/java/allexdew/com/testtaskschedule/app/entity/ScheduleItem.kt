package allexdew.com.testtaskschedule.app.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "schedule")
class ScheduleItem(
    @ColumnInfo(name = "s_item_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "s_name")
    var name: String = "",

    @ColumnInfo(name = "s_start_time")
    var startTime: String = "",

    @ColumnInfo(name = "s_end_time")
    var endTime: String = "",

    @ColumnInfo(name = "s_teacher")
    var teacher: String = "",

    @ColumnInfo(name = "s_place")
    var place: String = "",

    @ColumnInfo(name = "s_description")
    var description: String = "",

    @ColumnInfo(name = "s_week_day")
    var weekDay: Int = 0
)