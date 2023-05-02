package com.lego.myladder.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lego.myladder.domain.PULL_DOWNS_UID
import com.lego.myladder.domain.PULL_UPS_UID

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

    companion object {
        fun populateData(): List<Ladder> {
            return listOf(
                Ladder(
                    PULL_UPS_UID,
                    4, //size of sequence - 1
                    intArrayOf(5, 4, 3, 2, 1)
                ),
                Ladder(
                    PULL_DOWNS_UID,
                    4,
                    sequence = intArrayOf(5, 4, 3, 2, 1),
                )
            )
        }
    }
}