package com.universal.best.habittracker.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.squareup.timessquare.CalendarPickerView
import com.universal.best.habittracker.R
import com.universal.best.habittracker.databinding.AddActivityFragmentBinding
import java.util.*


class AddActivityDialogueFragment(val addActivityFinishInterface: AddActivityFinishInterface) :
    DialogFragment() {


    lateinit var binding: AddActivityFragmentBinding

    companion object {

        var selectedDates: ArrayList<Date>? = null;
    }

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(getContext()),
            R.layout.add_activity_fragment,
            null,
            false
        );
        var date: Date = Date();
        binding.calendarView.init(date, nextYear.getTime())
            .withSelectedDate(date)
            .inMode(CalendarPickerView.SelectionMode.RANGE);
        selectedDates = binding.calendarView.getSelectedDates() as ArrayList<Date>
        binding.clickHandler =
            MyClickHandler(
                this.context,
                binding.textView4,
                addActivityFinishInterface
            )

        return binding.root;
    }

    class MyClickHandler(
        val context: Context?,
        val activityName: TextView,
        val addActivityFinishInterface: AddActivityFinishInterface
    ) {


        fun onCLick(v: View) {
            when (v.id) {
                R.id.button -> {
                    context.let {
                        Toast.makeText(
                            context,
                            "Save to Db",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    if (activityName.text.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Activity name unfilled",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        addActivityFinishInterface.clickHandler(
                            activityName.text.toString(),
                            selectedDates
                        )
                    }
                }
            }
        }
    }


}

