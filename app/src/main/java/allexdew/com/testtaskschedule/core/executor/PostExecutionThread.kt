package allexdew.com.testtaskschedule.core.executor

import io.reactivex.Scheduler

interface PostExecutionThread {

    val scheduler: Scheduler

}
