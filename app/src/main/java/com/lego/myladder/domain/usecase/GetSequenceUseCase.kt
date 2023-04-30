package com.lego.myladder.domain.usecase

import com.lego.myladder.domain.Repository
import com.lego.myladder.domain.models.LadderModel

class GetSequenceUseCase(private val repository: Repository) {

    fun getLadders(): LadderModel{
        return repository.getLadders()
    }

}