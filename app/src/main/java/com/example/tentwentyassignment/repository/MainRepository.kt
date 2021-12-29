package com.example.tentwentyassignment.repository

import androidx.lifecycle.LiveData
import com.example.tentwentyassignment.models.OfflineMovieModel
import com.example.tentwentyassignment.models.UpcomingResult
import com.example.tentwentyassignment.network.BackEndApi
import com.example.tentwentyassignment.room.DAO
import com.example.tentwentyassignment.utils.Constants

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class MainRepository @Inject constructor(
    private val backEndApi: BackEndApi,
) {
    suspend fun getUpcomingMovies() =
        backEndApi.getUpcomingMovies(Constants.API_KEY)

    suspend fun getDetailPageData(keyword : Int) =
            backEndApi.getDetailPage(keyword, Constants.API_KEY)


//    //Room
//    suspend fun insert(moviesModel: List<UpcomingResult>) {
//        mDao.insertData(moviesModel)
//    }


}