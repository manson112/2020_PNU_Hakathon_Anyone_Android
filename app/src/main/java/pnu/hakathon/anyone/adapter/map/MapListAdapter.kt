package pnu.hakathon.anyone.adapter.map

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.map_list_item.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.model.SearchModel
import kotlin.math.roundToInt

class MapListAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<MapListAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<SearchModel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.map_list_item, parent, false))
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
        ).into(holder.itemView.map_item_image)

        holder.itemView.map_item_name.text = current.name
        holder.itemView.map_item_hashtag.text = current.hashTag
        holder.itemView.map_item_address.text = current.address

        val p = ((current.current.toFloat() / current.total.toFloat()) * 100).roundToInt()
        holder.itemView.map_item_percent.text = "$p%"
        holder.itemView.map_item_progress.progress = p
    }

    internal fun setList(list: List<SearchModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}
