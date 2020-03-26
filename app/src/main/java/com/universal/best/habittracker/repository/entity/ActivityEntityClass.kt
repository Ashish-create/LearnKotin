package com.universal.best.habittracker.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.universal.best.habittracker.repository.TypeConverters.DateTypeConverter
import java.util.*

@Entity(tableName = "activity_list_table")
@TypeConverters(DateTypeConverter::class)
 data class ActivityEntityClass(


    @ColumnInfo(name = "startdate")
    var startDate: Date,

    @ColumnInfo(name = "enddate")
    var enddate: Date,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "activityName")
    var activityName: String

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
