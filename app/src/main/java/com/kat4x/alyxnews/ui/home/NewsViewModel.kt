package com.kat4x.alyxnews.ui.home

import android.net.Uri
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kat4x.alyxnews.NewsApiService
import com.kat4x.alyxnews.models.innerUse.ItemNews
import com.kat4x.alyxnews.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {

    private var repository: NewsRepository = NewsRepository(NewsApiService.serviceApi)
    val newsList = MutableLiveData<MutableList<ItemNews>>()
    var isRefresh = MutableLiveData(false)
    var link = MutableLiveData<String>()

    @WorkerThread
    fun getNews() {
        try {
            CoroutineScope(IO).launch {
                newsList.postValue(repository.getNewsItems())
                isRefresh.postValue(false)
            }
        } catch (e: Exception) {
            isRefresh.postValue(false)
        }
    }
}