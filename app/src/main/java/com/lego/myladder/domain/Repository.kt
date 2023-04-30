package com.lego.myladder.domain

import com.lego.myladder.domain.models.Ladder
import com.lego.myladder.domain.models.LadderModel

interface Repository {

    fun increase(newLadder: Ladder)

    fun getLadders(): LadderModel

}