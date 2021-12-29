package com.example.tentwentyassignment.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tentwentyassignment.models.OfflineMovieModel
import com.example.tentwentyassignment.models.UpcomingResult

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(offlineMovieModel: List<OfflineMovieModel>)

    @Query("SELECT * FROM MOVIES")
    fun getAll(): List<OfflineMovieModel>


}