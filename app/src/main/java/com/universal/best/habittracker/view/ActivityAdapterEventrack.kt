package com.universal.best.habittracker.view

import com.universal.best.habittracker.repository.entity.ActivityEntityClass

interface ActivityAdapterEventrack {

    fun deleteTriggered(model:ActivityEntityClass);
    fun viewChartTriggered()
    fun imageCharTriggered()

}