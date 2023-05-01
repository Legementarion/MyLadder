package com.lego.myladder.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lego.myladder.data.models.Ladder

@Dao
interface LadderDao {
    @Query("SELECT * FROM ladder")
    fun getAll(): List<Ladder>

    @Insert
    fun insertAll(vararg users: Ladder)

}