package allexdew.com.testtaskschedule.ui.main

import allexdew.com.testtaskschedule.R
import allexdew.com.testtaskschedule.app.entity.ScheduleItem
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), MainView, ScheduleAdapter.OnItemClickListener {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private val scheduleAdapter = ScheduleAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lm = LinearLayoutManager(this)
        rvScheduleList.layoutManager = lm
        rvScheduleList.itemAnimator = DefaultItemAnimator()
        rvScheduleList.addItemDecoration(DividerItemDecoration(this, lm.orientation))
        rvScheduleList.adapter = scheduleAdapter

        srlContent.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
        srlContent.setOnRefreshListener { presenter.loadData() }
    }

    override fun onItemClick(position: Int) {
        scheduleAdapter.getItem(position)
        //do some
    }

    override fun updateSchedule(list: List<ScheduleItem>) {
        scheduleAdapter.updateItems(list)
    }

    override fun startLoad() {
        srlContent.isRefreshing = true
    }

    override fun loadDone() {
        srlContent.isRefreshing = false
    }

    override fun showLoadError(error: Throwable) {
        Toast.makeText(this, "Load error: ${error.message}.", Toast.LENGTH_LONG).show()
    }
}
