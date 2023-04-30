package com.lego.myladder.data.source

import com.lego.myladder.domain.models.Ladder

interface LocalDataSource {
    fun update(ladder: Ladder)
    fun getLadder(): Ladder
}