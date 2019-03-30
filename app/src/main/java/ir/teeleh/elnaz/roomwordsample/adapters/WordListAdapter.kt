package ir.teeleh.elnaz.roomwordsample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.teeleh.elnaz.roomwordsample.R
import ir.teeleh.elnaz.roomwordsample.data.Word


class WordListAdapter : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private var mInflater: LayoutInflater? = null
    private var mWords: List<Word>? = null // Cached copy of words

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }

    @Synchronized
    fun getInflater(context: Context): LayoutInflater {
        return mInflater ?: LayoutInflater.from(context).also { mInflater = it }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = getInflater(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (mWords != null) {
            val (word) = mWords!![position]
            holder.wordItemView.text = word
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.text = "No Word"
        }
    }

    fun setWords(words: List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    override fun getItemCount(): Int {
        return if (mWords != null)
            mWords!!.size
        else
            0
    }
}