package com.sameer.colours

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel:ViewModel() {

    val appDao=MyApplication.appDatabase.getAppDao()
    val list :LiveData<List<ModelClassEntity>> = appDao.getAllColors()


    fun addEntity(entity: ModelClassEntity)
    {
        viewModelScope.launch(Dispatchers.IO) {
            appDao.insert(entity)
        }

    }
}