package pnu.hakathon.anyone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.bookmark_item.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.localdb.Bookmark
import pnu.hakathon.anyone.localdb.RecentlyViewedItem

class RecentlyViewedItemListAdapter internal constructor(
    context: Context
): RecyclerView.Adapter<RecentlyViewedItemListAdapter.RecentlyViewedItemViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var recentlyViewedItems = emptyList<RecentlyViewedItem>()

    inner class RecentlyViewedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlyViewedItemListAdapter.RecentlyViewedItemViewHolder {
        return RecentlyViewedItemViewHolder(inflater.inflate(R.layout.bookmark_item, parent, false))
    }

    override fun getItemCount() = recentlyViewedItems.size

    override fun onBindViewHolder(holder: RecentlyViewedItemListAdapter.RecentlyViewedItemViewHolder, position: Int) {
        val current = recentlyViewedItems[position]
//        holder.itemView.bookmark_textview.text = "${current.id} ${current.name}"
    }

    internal fun setBookmarks(rv: List<RecentlyViewedItem>) {
        this.recentlyViewedItems = rv
        notifyDataSetChanged()
    }

}