package ir.teeleh.elnaz.roomwordsample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ir.teeleh.elnaz.roomwordsample.data.Word
import ir.teeleh.elnaz.roomwordsample.data.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class WordViewModel(
    private var repository: WordRepository
) : ViewModel() {

    private var allWords: LiveData<List<Word>>? = null

    init {
        allWords = repository.getAllWords()
    }

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun getAllWords(): LiveData<List<Word>>? {
        return allWords
    }

    fun insert(word: Word) {
        uiScope.launch {
            repository?.insert(word)
        }
    }
}