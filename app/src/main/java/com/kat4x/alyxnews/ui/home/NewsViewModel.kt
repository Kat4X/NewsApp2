package com.kat4x.alyxnews.ui.home

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

class NewsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var repository: NewsRepository = NewsRepository(NewsApiService.serviceApi)
    //    val t = MutableLiveData<MutableList<ResponseNews.Article>>()
    val newsList = MutableLiveData<MutableList<ItemNews>>()
    var isRefresh = MutableLiveData(false)

    private val _text = MutableLiveData<String>().apply {

    }
    val text: LiveData<String> = _text

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