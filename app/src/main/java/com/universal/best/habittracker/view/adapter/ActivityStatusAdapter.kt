package com.universal.best.habittracker.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.universal.best.habittracker.R
import com.universal.best.habittracker.databinding.HabitStausItemBinding
import com.universal.best.habittracker.repository.entity.ActivityEntityClass

class ActivityStatusAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var dataBinder: HabitStausItemBinding;
    lateinit var stausViewHolder: StatusViewHolder
    var list: ArrayList<ActivityEntityClass>? = ArrayList(  )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        dataBinder = DataBindingUtil.inflate(
            LayoutInflater.from(parent.getContext()),
            R.layout.habit_staus_item, parent,
            false
        )

        stausViewHolder = StatusViewHolder(dataBinder);
        return stausViewHolder
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        stausViewHolder.binddata(list, position);

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class StatusViewHolder(val databinder: HabitStausItemBinding) :
        RecyclerView.ViewHolder(databinder.root) {

        fun binddata(list: List<ActivityEntityClass>?, position: Int) {
            databinder.model = list?.get(position)
          //  databinder.executePendingBindings()
        }

    }

    fun setAdapter(list: List<ActivityEntityClass>?) {
        list?.forEach { i -> this.list?.add(i) }
    }

    fun clear()
    {
        this.list?.clear()
    }
}