package ir.teeleh.elnaz.roomwordsample.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ir.teeleh.elnaz.roomwordsample.data.Word
import ir.teeleh.elnaz.roomwordsample.data.WordRepository
import ir.teeleh.elnaz.roomwordsample.data.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class WordViewModel(
    application: Application
) : AndroidViewModel(application) {

    val allWords: LiveData<List<Word>>
    private val repository: WordRepository

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun insert(word: Word) {
        uiScope.launch {
            repository?.insert(word)
        }
    }
}