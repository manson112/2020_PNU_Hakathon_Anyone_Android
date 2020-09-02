package pnu.hakathon.anyone.view.adapter.bookmark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_bookmark.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.view.activity.StoreDetailActivity

class BookmarkListAdapter internal constructor(
    val context: Context
):RecyclerView.Adapter<BookmarkListAdapter.BookmarkViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var bookmarks = emptyList<StoreModel>()
    private var onClickedTime = System.currentTimeMillis()

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(inflater.inflate(R.layout.item_bookmark, parent, false)).apply {
            itemView.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
                val currentTime = System.currentTimeMillis()
                if (currentTime - onClickedTime > itemView.item_transformationLayout.duration) {
                    StoreDetailActivity.startActivity(
                        itemView.item_transformationLayout,
                        bookmarks[position]
                    )
                    onClickedTime = currentTime
                }
            }
        }
    }

    override fun getItemCount() = bookmarks.size

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val current = bookmarks[position]
        Glide.with(context).load(current.imageURL).placeholder(R.drawable.image_empty).into(holder.itemView.bookmark_item_store_image)
        holder.itemView.bookmark_item_store_name.text = current.storeName
    }
    internal fun setBookmarks(bookmarks: List<StoreModel>) {
        this.bookmarks = bookmarks
        notifyDataSetChanged()
    }
}