package pnu.hakathon.anyone.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_hash_fragment_item.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.model.HashStore

class HomeHashListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<HomeHashListAdapter.HomeHashListViewHolder>() {
    private val mContext = context
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var stores = emptyList<HashStore>()

    inner class HomeHashListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHashListViewHolder {
        return HomeHashListViewHolder(
            inflater.inflate(
                R.layout.home_hash_fragment_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = stores.size

    override fun onBindViewHolder(holder: HomeHashListViewHolder, position: Int) {
        val current = stores[position]
        Glide.with(mContext).load(current.imageURL)
            .into(holder.itemView.home_hash_fragment_item_image)
        holder.itemView.home_hash_fragment_item_text.text = current.name
    }

    internal fun setStores(hs: List<HashStore>) {
        this.stores = hs
        notifyDataSetChanged()
    }
}