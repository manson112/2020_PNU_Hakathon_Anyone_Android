package pnu.hakathon.anyone.adapter.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.searchhistory_item.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.localdb.SearchHistory

class SearchHistoryListAdapter internal constructor(
    context: Context
): RecyclerView.Adapter<SearchHistoryListAdapter.SearchHistoryViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var histories = emptyList<SearchHistory>()

    inner class SearchHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        return SearchHistoryViewHolder(inflater.inflate(R.layout.searchhistory_item, parent, false))
    }

    override fun getItemCount() = histories.size

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        val current = histories[position]
        holder.itemView.searchhistory_textview.text = current.searchQuery
    }

    internal fun setHistories(histories: List<SearchHistory>) {
        this.histories = histories
        notifyDataSetChanged()
    }
}