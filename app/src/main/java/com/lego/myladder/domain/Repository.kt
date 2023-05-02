package com.lego.myladder.domain

import androidx.lifecycle.LiveData
import com.lego.myladder.domain.models.Ladder
import com.lego.myladder.domain.models.LadderModel

interface Repository {

    fun increase(newLadder: Ladder)

    fun getLadders(): LiveData<LadderModel?>

}