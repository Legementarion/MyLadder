package com.lego.myladder.data.repository

import androidx.lifecycle.LiveData
import com.lego.myladder.data.source.LocalDataSource
import com.lego.myladder.domain.Repository
import com.lego.myladder.domain.models.Ladder
import com.lego.myladder.domain.models.LadderModel

class RepositoryImpl(private val dataSource: LocalDataSource) : Repository {

    override fun increase(newLadder: Ladder) {
        dataSource.update(newLadder)
    }

    override fun getLadders(): LiveData<LadderModel?> {
        return dataSource.getLadders()
    }
}