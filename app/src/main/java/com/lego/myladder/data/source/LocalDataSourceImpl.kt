package com.lego.myladder.data.source

import com.lego.myladder.domain.models.Ladder

class LocalDataSourceImpl: LocalDataSource {

    override fun update(ladder: Ladder) {
    }

    override fun getLadder(): Ladder {
        return Ladder()
    }

}