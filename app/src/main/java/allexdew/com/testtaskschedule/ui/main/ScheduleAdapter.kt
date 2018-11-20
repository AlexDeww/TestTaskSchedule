package allexdew.com.testtaskschedule.ui.main

import allexdew.com.testtaskschedule.R
import allexdew.com.testtaskschedule.app.entity.ScheduleItem
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.schedule_list_item.view.*
import java.text.DateFormatSymbols

class ScheduleAdapter(
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    private var data: List<ScheduleItem> = listOf()

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.schedule_list_item, parent, false)
        return ScheduleViewHolder(v, itemClickListener)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun getItem(position: Int): ScheduleItem = data[position]

    fun updateItems(items: List<ScheduleItem>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                data[oldItemPosition].id == items[newItemPosition].id

            override fun getOldListSize(): Int = data.size

            override fun getNewListSize(): Int = items.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                data[oldItemPosition].name == items[newItemPosition].name
        })
        data = items
        diffResult.dispatchUpdatesTo(this)
    }


    class ScheduleViewHolder(
        itemView: View,
        private val clickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val tvAllData = itemView.tvAllData

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position == RecyclerView.NO_POSITION) return

            clickListener.onItemClick(position)
        }

        fun bind(scheduleItem: ScheduleItem) {
            tvAllData.text = itemView.context.getString(R.string.schedule_data,
                scheduleItem.name,
                scheduleItem.startTime,
                scheduleItem.endTime,
                scheduleItem.teacher,
                scheduleItem.place,
                scheduleItem.description,
                DateFormatSymbols.getInstance().weekdays[(scheduleItem.weekDay % 7) + 1]
            )
        }

    }

}