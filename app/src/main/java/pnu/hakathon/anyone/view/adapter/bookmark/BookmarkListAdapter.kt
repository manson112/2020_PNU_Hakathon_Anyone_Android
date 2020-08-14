package pnu.hakathon.anyone.view.adapter.bookmark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.bookmark_item.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.entity.Bookmark
import pnu.hakathon.anyone.viewmodel.BookmarkViewModel

class BookmarkListAdapter internal constructor(
    val context: Context,
    private val viewModel: BookmarkViewModel
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
        Glide.with(context).load(current.imageURL).into(holder.itemView.bookmark_item_store_image)
        holder.itemView.bookmark_item_store_category.text = current.categoryName
        holder.itemView.bookmark_item_store_name.text = current.storeName
        holder.itemView.bookmark_item_store_address.text = current.address
        holder.itemView.bookmark_item_created_at.text = "2020-07-02"
        holder.itemView.setOnClickListener {
            viewModel.selectBookmark(position)
        }
    }

    internal fun setBookmarks(bookmarks: List<Bookmark>) {
        this.bookmarks = bookmarks
        notifyDataSetChanged()
    }

}