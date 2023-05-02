package com.lego.myladder.data.source

import androidx.lifecycle.LiveData
import com.lego.myladder.domain.models.Ladder
import com.lego.myladder.domain.models.LadderModel

interface LocalDataSource {
    fun update(ladder: Ladder)
    fun getLadders(): LiveData<LadderModel?>
}