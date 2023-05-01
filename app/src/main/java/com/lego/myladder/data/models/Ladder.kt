package com.lego.myladder.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
data class Ladder(
    @PrimaryKey val uid: Int,
    val pointer: Int,
    @field:TypeConverters(IntTypeConverter::class)
    val sequence: IntArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ladder

        if (uid != other.uid) return false
        if (pointer != other.pointer) return false
        if (!sequence.contentEquals(other.sequence)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uid
        result = 31 * result + pointer
        result = 31 * result + sequence.contentHashCode()
        return result
    }
}