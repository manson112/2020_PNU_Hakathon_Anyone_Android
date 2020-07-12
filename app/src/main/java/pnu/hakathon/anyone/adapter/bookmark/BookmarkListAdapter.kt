package pnu.hakathon.anyone.adapter.bookmark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.bookmark_item.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.localdb.Bookmark

class BookmarkListAdapter internal constructor(
    context: Context
):RecyclerView.Adapter<BookmarkListAdapter.BookmarkViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var bookmarks = emptyList<Bookmark>()

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(inflater.inflate(R.layout.bookmark_item, parent, false))
    }

    override fun getItemCount() = bookmarks.size

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val current = bookmarks[position]
        holder.itemView.bookmark_textview.text = "${current.id} ${current.storeName}"
    }

    internal fun setBookmarks(bookmarks: List<Bookmark>) {
        this.bookmarks = bookmarks
        notifyDataSetChanged()
    }

}