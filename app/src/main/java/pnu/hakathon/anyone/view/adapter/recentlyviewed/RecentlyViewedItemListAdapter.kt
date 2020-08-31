package pnu.hakathon.anyone.view.adapter.recentlyviewed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.entity.RecentlyViewedItem

class RecentlyViewedItemListAdapter internal constructor(
    context: Context
): RecyclerView.Adapter<RecentlyViewedItemListAdapter.RecentlyViewedItemViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var recentlyViewedItems = emptyList<RecentlyViewedItem>()

    inner class RecentlyViewedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentlyViewedItemViewHolder {
        return RecentlyViewedItemViewHolder(inflater.inflate(R.layout.item_bookmark, parent, false))
    }

    override fun getItemCount() = recentlyViewedItems.size

    override fun onBindViewHolder(holder: RecentlyViewedItemViewHolder, position: Int) {
        val current = recentlyViewedItems[position]
//        holder.itemView.bookmark_textview.text = "${current.id} ${current.name}"
    }

    internal fun setBookmarks(rv: List<RecentlyViewedItem>) {
        this.recentlyViewedItems = rv
        notifyDataSetChanged()
    }

}