package com.lego.myladder.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lego.myladder.domain.models.Ladder
import com.lego.myladder.domain.models.LadderModel
import com.lego.myladder.domain.usecase.GetSequenceUseCase
import com.lego.myladder.domain.usecase.IncreaseSequenceUseCase
import com.lego.myladder.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    getSequenceUseCase: GetSequenceUseCase,
    private val increaseSequenceUseCase: IncreaseSequenceUseCase
) : BaseViewModel() {

    private var _myData: LiveData<LadderModel?> = getSequenceUseCase.getLadders()
    val ladders: LiveData<LadderModel?>
        get() = _myData

    val upIndex = MutableLiveData<Int>().apply { postValue(DEFAULT) }
    val downIndex = MutableLiveData<Int>().apply { postValue(DEFAULT) }

    fun resetToday() {
        upIndex.postValue(DEFAULT)
        downIndex.postValue(DEFAULT)
    }

    fun next(ladder: Ladder) {
        if (this.ladders.value?.up == ladder) {
            goForNext(upIndex)
        } else {
            goForNext(downIndex)
        }
    }

    private fun goForNext(index: MutableLiveData<Int>) {
        var current = index.value ?: DEFAULT
        if (current != MAX_STEP) {
            current += STEP
        }
        index.postValue(current)
        checkSteps()
    }

    private fun checkSteps() {
        if (upIndex.value == MAX_STEP && downIndex.value == MAX_STEP) {
            playFinalDayAnimation()
        }
    }

    private fun playFinalDayAnimation() {
        //todo add lottie animation
    }

    fun increment(currentLadder: Ladder) {
        viewModelScope.launch {
            increaseSequenceUseCase.increase(currentLadder)
        }
        if (this.ladders.value?.up == currentLadder) {
            upIndex.postValue(DEFAULT)
        } else {
            downIndex.postValue(DEFAULT)
        }
    }

    fun hardReset() {

    }

    companion object {
        private const val DEFAULT = 0
        private const val STEP = 1
        private const val MAX_STEP = 4
    }

}