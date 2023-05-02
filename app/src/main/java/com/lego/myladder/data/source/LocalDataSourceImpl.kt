package com.lego.myladder.data.source

import androidx.lifecycle.LiveData
import com.lego.myladder.data.dao.LadderDao
import com.lego.myladder.data.models.toData
import com.lego.myladder.data.models.toDomain
import com.lego.myladder.domain.models.Ladder
import com.lego.myladder.domain.models.LadderModel

class LocalDataSourceImpl(private val dao: LadderDao) : LocalDataSource {

    override fun update(ladder: Ladder) {
        dao.insertAll(ladder.toData())
    }

    override fun getLadders(): LiveData<LadderModel?> {
        return dao.getAll().toDomain()
    }

}