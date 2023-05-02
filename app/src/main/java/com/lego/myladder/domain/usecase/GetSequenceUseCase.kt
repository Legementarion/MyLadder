package com.lego.myladder.domain.usecase

import androidx.lifecycle.LiveData
import com.lego.myladder.domain.Repository
import com.lego.myladder.domain.models.LadderModel

class GetSequenceUseCase(private val repository: Repository) {

    fun getLadders(): LiveData<LadderModel?> {
        return repository.getLadders()
    }

}