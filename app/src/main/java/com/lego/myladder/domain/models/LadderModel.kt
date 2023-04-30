package com.lego.myladder.domain.models

data class LadderModel(
    var up: Ladder,
    var down: Ladder
)

data class Ladder(
    var sequence: IntArray = intArrayOf(1, 2, 3, 4, 5),
    var pointer: Int = sequence.lastIndex,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ladder

        if (pointer != other.pointer) return false
        if (!sequence.contentEquals(other.sequence)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pointer
        result = 31 * result + sequence.contentHashCode()
        return result
    }
}