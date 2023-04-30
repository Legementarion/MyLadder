package com.lego.myladder.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lego.myladder.domain.models.Ladder
import com.lego.myladder.domain.models.LadderModel

class MainViewModel : ViewModel() {

    val ladder = MutableLiveData<LadderModel>().apply {
        postValue(
            LadderModel(
                Ladder(
                    0,
                    sequenceOf(5, 4, 3, 2, 2)
                ),
                Ladder(
                    0,
                    sequenceOf(5, 4, 3, 2, 1)
                )
            )
        )
    }
    val upIndex = MutableLiveData<Int>().apply { postValue(0) }
    val downIndex = MutableLiveData<Int>().apply { postValue(0) }

    fun resetToday() {
        upIndex.postValue(0)
        downIndex.postValue(0)
    }

    fun next(ladder: Ladder) {
        ladder.index += 1
        if (this.ladder.value?.up == ladder) {
            upIndex.postValue(ladder.index)
        }   else {
            downIndex.postValue(ladder.index)
        }
    }

    fun increment() {

    }

    fun hardReset() {

    }

}