package com.universal.best.habittracker.repository.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.universal.best.habittracker.repository.entity.ActivityEntityClass

@Dao
interface AcitivityClassDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun inserActivty(entry: ActivityEntityClass):Long

    @Delete
    suspend fun deleteEntityFromDB(model: ActivityEntityClass)

    @Query("Delete from activity_list_table where activityName =:actname")
    fun deleteAcitivity(actname: String)

    @Query("SELECT * FROM activity_list_table ORDER By id DESC")
    suspend fun retrieveAllActivities(): List<ActivityEntityClass>;

    @Query("SELECT * FROM activity_list_table where startdate>= :cuurentDate AND enddate<=:cuurentDate")
    fun getActivitiesByDate(cuurentDate: Long): LiveData<List<ActivityEntityClass>>

    @Query("UPDATE activity_list_table SET status=:act_status WHERE activityName=:actName")
    fun updatestatus(actName: String, act_status: String)

}