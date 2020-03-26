package com.universal.best.habittracker.repository.respository

import com.universal.best.habittracker.repository.Dao.AcitivityClassDao
import com.universal.best.habittracker.repository.entity.ActivityEntityClass
import java.util.*

class ActivityRepository(private val dao: AcitivityClassDao?) {

    suspend fun saveintoDB(activityname: String, startDate: Date, endDate: Date, status:String):Long {
        var entry  =ActivityEntityClass(startDate,endDate,"0",activityname);
       return dao?.inserActivty(entry)?:-1
    }

    suspend fun retreiveFromDb():List<ActivityEntityClass>? {
       return dao?.retrieveAllActivities();
    }

    fun deleteFromDB(activityname: String) {
        dao?.deleteAcitivity(activityname)
    }

    suspend fun deleteEntityFromDB(model: ActivityEntityClass) {
        dao?.deleteEntityFromDB(model)
    }

    fun getActivityBydate(date: Date) {
        dao?.getActivitiesByDate(date.time)
    }

    fun updateActivityStatus(activity:String,status:String) {
        dao?.updatestatus(activity,status)
    }


}