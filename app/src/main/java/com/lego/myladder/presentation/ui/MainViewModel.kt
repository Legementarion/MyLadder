package com.lego.myladder.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lego.myladder.domain.models.Ladder
import com.lego.myladder.domain.models.LadderModel
import com.lego.myladder.domain.usecase.GetSequenceUseCase
import com.lego.myladder.domain.usecase.IncreaseSequenceUseCase

class MainViewModel(
    getSequenceUseCase: GetSequenceUseCase,
    private val increaseSequenceUseCase: IncreaseSequenceUseCase
) : ViewModel() {

    val ladders = MutableLiveData<LadderModel>()
    val upIndex = MutableLiveData<Int>().apply { postValue(DEFAULT) }
    val downIndex = MutableLiveData<Int>().apply { postValue(DEFAULT) }

    init {
        ladders.postValue(getSequenceUseCase.getLadders())
    }

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

    private fun checkSteps(){
        if (upIndex.value == MAX_STEP && downIndex.value == MAX_STEP){
            playFinalDayAnimation()
        }
    }

    private fun playFinalDayAnimation() {
        //todo add lottie animation
    }

    fun increment(currentLadder: Ladder) {
        increaseSequenceUseCase.increase(currentLadder)
    }

    fun hardReset() {

    }

    companion object {
        private const val DEFAULT = 0
        private const val STEP = 1
        private const val MAX_STEP = 4
    }

}