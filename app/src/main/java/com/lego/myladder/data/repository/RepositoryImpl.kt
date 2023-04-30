package com.lego.myladder.data.repository

import com.lego.myladder.data.source.LocalDataSource
import com.lego.myladder.domain.Repository
import com.lego.myladder.domain.models.Ladder
import com.lego.myladder.domain.models.LadderModel

class RepositoryImpl(private val dataSource: LocalDataSource) : Repository {

    override fun increase(newLadder: Ladder) {
        dataSource.update(newLadder)
    }

    override fun getLadders(): LadderModel {
        return LadderModel(
            Ladder(
                intArrayOf(5, 4, 3, 2, 2),
                0
            ),
            Ladder(
                intArrayOf(5, 4, 3, 2, 1),
                0
            )
        )
    }
}