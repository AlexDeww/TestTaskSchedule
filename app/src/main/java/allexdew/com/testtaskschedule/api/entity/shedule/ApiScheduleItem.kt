package allexdew.com.testtaskschedule.api.entity.shedule

data class ApiScheduleItem(
    val name: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val teacher: String = "",
    val place: String = "",
    val description: String = "",
    val weekDay: Int = 0
)