package ir.teeleh.elnaz.roomwordsample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.teeleh.elnaz.roomwordsample.data.WordRepository

class WordViewModelFactory(
    private val repository: WordRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WordViewModel(repository) as T
    }

}