package com.universal.best.habittracker.view.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.universal.best.habittracker.R
import com.universal.best.habittracker.databinding.TopActivitiesItemLayoutBinding
import com.universal.best.habittracker.repository.entity.ActivityEntityClass
import com.universal.best.habittracker.view.ActivityAdapterEventrack
import com.universal.best.habittracker.view.MainActivity
import com.universal.best.habittracker.view.MainActivity.Companion.context
import com.universal.best.habittracker.view.MainActivity.Companion.mainActivityContext


class ActivityListAdapter(var adaptercallBackInterface: ActivityAdapterEventrack) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<ActivityEntityClass>? = null
    lateinit var databinder: TopActivitiesItemLayoutBinding;
    lateinit var myViewHolder: MyViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        databinder = DataBindingUtil.inflate(
            LayoutInflater.from(parent.getContext()),
            R.layout.top_activities_item_layout, parent,
            false
        )

        myViewHolder = MyViewHolder(databinder);
        var Handler=MyClickHandler(adaptercallBackInterface)
        databinder.myclickHanlder=Handler
        return myViewHolder;
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        myViewHolder.bindData(list,position);

    }
    override fun getItemViewType(position: Int): Int {
        return position
    }
    fun setAdapterData(list: List<ActivityEntityClass>?) {
        this.list = list

    }

    class MyViewHolder(val databinder: TopActivitiesItemLayoutBinding) :
        RecyclerView.ViewHolder(databinder.root) {

        fun bindData(
            list: List<ActivityEntityClass>?,
            i: Int
        ) {
            databinder.model=list?.get(i)
            databinder.executePendingBindings()

        }
    }

    class MyClickHandler(var eventrack: ActivityAdapterEventrack)
    {
        fun onclick(view: View, entityClass: ActivityEntityClass)
        {
            when(view.id)
            {
                R.id.imageView2->{
                    //open dialogbox and on yes delete it from db..
                    val dialogClickListener =
                        DialogInterface.OnClickListener { dialog, which ->
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                   eventrack.deleteTriggered(entityClass)
                                }
                                DialogInterface.BUTTON_NEGATIVE -> {
                                }
                            }
                        }

                    var builder: AlertDialog.Builder = AlertDialog.Builder(mainActivityContext)

                    builder.setMessage("Are you sure to delete this activity?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show()
                }
                R.id.imageView4->{
                    //open new actiivty with intent
                }


            }
        }


    }
}