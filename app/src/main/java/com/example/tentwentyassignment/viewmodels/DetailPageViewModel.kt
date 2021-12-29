package com.example.tentwentyassignment.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tentwentyassignment.models.DetailPageModel
import com.example.tentwentyassignment.models.UpcomingMoviesModel
import com.example.tentwentyassignment.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
public class DetailPageViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    ) : ViewModel() {

    val mDetailPageResponse = MutableLiveData<DetailPageModel>()
    var loadingError = MutableLiveData<String?>()
    var loading = MutableLiveData<Boolean>()
    var job: Job? = null
    var exceptionalHandling = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exceptional Error: ${throwable.localizedMessage}")
    }

    private fun onError(message: String) {
        loadingError.value = message
        loading.value = false
    }

    fun callDetailPageData(keyword : Int) {
        job = CoroutineScope(Dispatchers.IO + exceptionalHandling).launch {
            val response = mainRepository.getDetailPageData(keyword)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        mDetailPageResponse.value = response.body()
                        loadingError.value = null
                        loading.value = false
                    } else {
                        onError("UserLoadError : ${response.message()} ")
                        loading.value = false
                    }
                } catch (e: SocketTimeoutException) {
                    onError("UserLoadError : timeout ")
                    loading.value = false
                }
            }
        }
    }
}