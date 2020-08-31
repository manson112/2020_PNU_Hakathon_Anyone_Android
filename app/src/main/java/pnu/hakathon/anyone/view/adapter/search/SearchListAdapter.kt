package pnu.hakathon.anyone.view.adapter.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_search.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.model.SearchModel

class SearchListAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<SearchModel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_search, parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        Glide.with(context).load(
            context.resources.getIdentifier(
                current.imageURL,
                "drawable",
                context.packageName
            )
        ).into(holder.itemView.search_item_image)
        holder.itemView.search_item_name.text = current.name
        holder.itemView.search_item_hashtag.text = current.hashTag
        holder.itemView.search_item_address.text = current.address
    }

    internal fun setList(list: List<SearchModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}
