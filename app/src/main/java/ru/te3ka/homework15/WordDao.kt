package ru.te3ka.homework15

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Update
    suspend fun update(word: Word)

    @Query("SELECT * FROM word_table ORDER BY count DESC LIMIT 5")
    fun getTopWords(): Flow<List<Word>>

    @Query("SELECT * FROM word_table WHERE word = :word")
    suspend fun getWord(word: String): Word?

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}