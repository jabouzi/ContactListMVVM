package com.raywenderlich.android.imet.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raywenderlich.android.imet.data.model.People

@Dao
interface PeopleDao {
    @Query("SELECT * FROM People ORDER BY id DESC")
    fun getAll(): LiveData<List<People>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(people: People)

    @Query("DELETE FROM People")
    fun deleteAll()

    @Query("SELECT * FROM People WHERE id = :id")
    fun find(id: Int): LiveData<People>

    @Query("SELECT * FROM People WHERE name LIKE '%' || :name || '%'")
    fun findBy(name: String): LiveData<List<People>>
}