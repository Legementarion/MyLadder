package com.lego.myladder.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lego.myladder.data.models.Ladder

@Dao
interface LadderDao {

    @Query("SELECT * FROM ladder")
    fun getAll(): LiveData<List<Ladder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg ladder: Ladder)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(ladder: List<Ladder>)

}