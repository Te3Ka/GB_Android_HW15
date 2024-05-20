package ru.te3ka.homework15

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey
    val word: String,
    val count: Int
)
