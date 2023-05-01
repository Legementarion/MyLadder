package com.lego.myladder.data.models

import androidx.room.TypeConverter
import com.lego.myladder.domain.models.LadderModel

fun List<Ladder>.toDomain(): LadderModel {
    return LadderModel(
        up = this.first().toDomain(),
        down = this.last().toDomain()
    )
}

fun Ladder.toDomain(): com.lego.myladder.domain.models.Ladder {
    return com.lego.myladder.domain.models.Ladder(
        this.sequence,
        this.pointer,
        this.uid,
    )
}

fun com.lego.myladder.domain.models.Ladder.toData(): Ladder {
    return Ladder(
        this.uid,
        this.pointer,
        this.sequence
    )
}

class IntTypeConverter {
    @TypeConverter
    fun saveIntList(array: IntArray): String {
        return array.joinToString(" ")
    }

    @TypeConverter
    fun getIntArray(string: String): IntArray {
        return string.split(" ").map { it.toInt() }.toIntArray()
    }
}