package com.universal.best.habittracker.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.universal.best.habittracker.R


import com.universal.best.habittracker.databinding.ActivityMainBinding
import com.universal.best.habittracker.repository.entity.ActivityEntityClass
import com.universal.best.habittracker.view.adapter.ActivityListAdapter
import com.universal.best.habittracker.view.adapter.ActivityStatusAdapter
import com.universal.best.habittracker.viewmodel.MainAcitivityViewModelProvider
import java.util.*

class MainActivity() : AppCompatActivity(),
    AddActivityFinishInterface, ActivityAdapterEventrack {

    lateinit private var mainActivityDataBinding: ActivityMainBinding
    lateinit private var viewModel: MainAcitivityViewModelProvider;
    val fragmentManager: FragmentManager = supportFragmentManager;



    companion object {
        var newFragment: DialogFragment? = null
        internal lateinit var context: Context
        internal lateinit var mainActivityContext: Context

        fun setContext(con: Context, mainActivityContextt: Context) {
            context = con
            mainActivityContext = mainActivityContextt
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityDataBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        );


        setContext(
            applicationContext, this
        );
        viewModel = ViewModelProvider(this).get(MainAcitivityViewModelProvider::class.java)
        var handleCLick =
            HandleCLick(
                this,
                fragmentManager, viewModel,
                this
            );

        mainActivityDataBinding.clickHandler = handleCLick;
        var listAdapter = ActivityListAdapter(this);

        mainActivityDataBinding.listAdapter = listAdapter;
        var statusAdapter = ActivityStatusAdapter();

        mainActivityDataBinding.statusAdaptor = statusAdapter
        mainActivityDataBinding.bottomRecylerView.setHasFixedSize(false);

        viewModel.retreiveFromDb()
            .observe(this, androidx.lifecycle.Observer { list ->
                list?.let {
                    listAdapter.setAdapterData(list)
                    listAdapter.notifyDataSetChanged()
                    statusAdapter.setAdapter(list)
                    statusAdapter.notifyDataSetChanged()
                }
            })

       viewModel.getSaveres()?.observe(this, androidx.lifecycle.Observer { viewModel.retreiveFromDb()
           .observe(this, androidx.lifecycle.Observer { list ->
               list?.let {
                   listAdapter.setAdapterData(list)
                   listAdapter.notifyDataSetChanged()
                   statusAdapter.clear()
                   statusAdapter.setAdapter(list)
                   statusAdapter.notifyDataSetChanged()
               }
           })
       })

    }

    class HandleCLick(
        val context: Context,
        val fragmentManager: FragmentManager, val viewmodel: MainAcitivityViewModelProvider,

        val addActivityFinishInterface: AddActivityFinishInterface
    ) {

        fun onClick(view: View) {
            when (view.id) {
                R.id.imageView -> {
                    newFragment =
                        AddActivityDialogueFragment(
                            addActivityFinishInterface
                        )
                    newFragment?.show(fragmentManager, "dialog")
                };

                else -> {
                    Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT).show()
                };

            }
        }
    }

    @Override
    override fun clickHandler(activtyName: String, list: ArrayList<Date>?) {
        Toast.makeText(this, activtyName, Toast.LENGTH_SHORT).show()
        newFragment?.dismiss()
        var len: Int = list?.size ?: 0
        when {
            len == 1 -> {
                Toast.makeText(this, "Valid date range  selected ", Toast.LENGTH_SHORT)
                    .show()
                list?.let {
                    viewModel.saveintoDB(activtyName, list.get(0), list.get(len - 1), "0") }
            }
            else -> Toast.makeText(this, "Valid date range  not selected ", Toast.LENGTH_SHORT)
                .show()

        }
    }

    @Override
    override fun onDestroy() {
        super.onDestroy()
        viewModel.close();
    }

    override fun deleteTriggered(model: ActivityEntityClass) {

        viewModel.deleteEntityFromDB(model)
    }

    override fun viewChartTriggered() {

    }

    override fun imageCharTriggered() {

    }


}
