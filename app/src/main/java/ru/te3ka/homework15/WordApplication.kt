package ru.te3ka.homework15

import android.app.Application

class WordApplication : Application() {
    val database by lazy { WordDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}