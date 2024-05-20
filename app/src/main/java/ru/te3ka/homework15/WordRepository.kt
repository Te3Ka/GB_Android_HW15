package ru.te3ka.homework15

import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {
    val topWords: Flow<List<Word>> = wordDao.getTopWords()

    suspend fun insertOrUpdate(word: Word) {
        val existingWord = wordDao.getWord(word.word)
        if (existingWord != null) {
            val updatedWord = existingWord.copy(count = existingWord.count + 1)
            wordDao.update(updatedWord)
        } else {
            wordDao.insert(word)
        }
    }

    suspend fun deleteAll() {
        wordDao.deleteAll()
    }
}