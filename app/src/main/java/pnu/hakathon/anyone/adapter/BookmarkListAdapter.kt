package pnu.hakathon.anyone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.bookmark_item.view.*
import kotlinx.android.synthetic.main.searchhistory_item.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.localdb.Bookmark
import pnu.hakathon.anyone.localdb.SearchHistory

class BookmarkListAdapter internal constructor(
    context: Context
):RecyclerView.Adapter<BookmarkListAdapter.BookmarkViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var bookmarks = emptyList<Bookmark>()

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkListAdapter.BookmarkViewHolder {
        return BookmarkViewHolder(inflater.inflate(R.layout.bookmark_item, parent, false))
    }

    override fun getItemCount() = bookmarks.size

    override fun onBindViewHolder(holder: BookmarkListAdapter.BookmarkViewHolder, position: Int) {
        val current = bookmarks[position]
        holder.itemView.bookmark_textview.text = "${current.id} ${current.name}"
    }

    internal fun setBookmarks(bookmarks: List<Bookmark>) {
        this.bookmarks = bookmarks
        notifyDataSetChanged()
    }

}