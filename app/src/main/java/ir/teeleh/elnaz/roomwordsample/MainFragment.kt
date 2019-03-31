package ir.teeleh.elnaz.roomwordsample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.teeleh.elnaz.roomwordsample.adapters.WordListAdapter
import ir.teeleh.elnaz.roomwordsample.data.Word
import ir.teeleh.elnaz.roomwordsample.viewmodels.WordViewModel


class MainFragment : Fragment() {
    private lateinit var wordViewModel: WordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val adapter = WordListAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        wordViewModel = ViewModelProviders.of(this@MainFragment).get(WordViewModel::class.java)
        subscribeUi(adapter)
        return view
    }

    private fun subscribeUi(adapter: WordListAdapter) {
        wordViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MainActivity.NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            data?.let {
                val word = Word(it.getStringExtra(NewWordActivity.EXTRA_REPLY))
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                context,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

}