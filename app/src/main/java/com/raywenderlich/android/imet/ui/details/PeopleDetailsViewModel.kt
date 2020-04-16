package com.raywenderlich.android.imet.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.raywenderlich.android.imet.IMetApp
import com.raywenderlich.android.imet.data.model.People

class PeopleDetailsViewModel(application: Application): AndroidViewModel(application) {
    private val peopleRepository = getApplication<IMetApp>().getPeopleRepository()
    private val peopleId = MutableLiveData<Int>()

    fun getPeopleDetails(id: Int): LiveData<People> {
        peopleId.value = id
        val peopleDetails = Transformations.switchMap<Int, People>(peopleId) {id ->
            peopleRepository.findPeople(id)
        }
        return peopleDetails
    }

    fun getPeopleDetails2(id: Int): LiveData<People> {
        return peopleRepository.findPeople(id)
    }
}