package ru.te3ka.homework15

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {
    val topWords: LiveData<List<Word>> = repository.topWords.asLiveData()

    fun insertOrUpdate(word: Word) {
        viewModelScope.launch {
            repository.insertOrUpdate(word)
        }
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}