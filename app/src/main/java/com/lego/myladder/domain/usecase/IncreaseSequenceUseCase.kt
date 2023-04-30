package com.lego.myladder.domain.usecase

import com.lego.myladder.domain.Repository
import com.lego.myladder.domain.models.Ladder

class IncreaseSequenceUseCase(private val repository: Repository) {

    fun increase(currentLadder: Ladder) {
        val newLadder = Ladder(
            sequence = currentLadder.sequence.also { it[currentLadder.pointer]++ },
            pointer = if (currentLadder.pointer != 0)
                currentLadder.pointer--
            else
                currentLadder.sequence.lastIndex,
        )
        repository.increase(newLadder)
    }

}