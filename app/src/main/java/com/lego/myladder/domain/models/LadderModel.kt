package com.lego.myladder.domain.models

data class LadderModel(
    var up: Ladder,
    var down: Ladder
)

data class Ladder(
    var index: Int = 0,
    var sequence: Sequence<Int> = sequenceOf(1, 2, 3, 4, 5)
)