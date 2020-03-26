package com.universal.best.habittracker.viewmodel

import androidx.lifecycle.*
import com.universal.best.habittracker.repository.Dao.AcitivityClassDao
import com.universal.best.habittracker.repository.Database.AcitivityDataBase
import com.universal.best.habittracker.repository.entity.ActivityEntityClass
import com.universal.best.habittracker.repository.respository.ActivityRepository
import com.universal.best.habittracker.view.MainActivity
import kotlinx.coroutines.launch
import java.util.*

class MainAcitivityViewModelProvider() : ViewModel() {

    private var repository: ActivityRepository? = null;
    private var dao: AcitivityClassDao? = null
    private var database: AcitivityDataBase? = null;
    var saveLiveData: MutableLiveData<Long> = MutableLiveData()


    init {
        database = AcitivityDataBase.getdataBaseInstance(MainActivity.context);
        dao = database?.getDao();
        repository = ActivityRepository(dao)

    }


    fun saveintoDB(activityname: String, startDate: Date, endDate: Date, status: String) {
        viewModelScope.launch {
            var saveSucces = repository?.saveintoDB(activityname, startDate, endDate, status)
            saveLiveData.postValue(saveSucces)
        }
    }

    fun retreiveFromDb(): LiveData<List<ActivityEntityClass>?> {
        val data: LiveData<List<ActivityEntityClass>?> = liveData {
            val list: List<ActivityEntityClass>? = repository?.retreiveFromDb();
            emit(list)
        }

        return data;
    }

    fun deleteFromDB(activityname: String) {
        repository?.deleteFromDB(activityname)
    }

    fun deleteEntityFromDB(model: ActivityEntityClass) {
        viewModelScope.launch { repository?.deleteEntityFromDB(model) }
    }

    fun getActivityBydate(date: Date) {
        repository?.getActivityBydate(date)
    }

    fun updateActivityStatus(activity: String, status: String) {
        repository?.updateActivityStatus("asf", status)
    }

    fun close() {
        if (database != null && database!!.isOpen) {
            database?.close()
        }
    }

   fun getSaveres():LiveData<Long>
   {
       return saveLiveData
   }


}