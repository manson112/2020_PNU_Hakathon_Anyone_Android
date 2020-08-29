package pnu.hakathon.anyone.view.adapter.map

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.map_list_item.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.entity.MapStoreModel
import kotlin.math.roundToInt

class MapListAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<MapListAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<MapStoreModel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.map_list_item, parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        Glide.with(context).load(
                current.imageURL
        ).into(holder.itemView.map_item_image)

        var tmpName = current.storeName
        if (tmpName.length > 9) {
            tmpName= tmpName.substring(IntRange(0, 7)) + "..."
        }
        holder.itemView.map_item_name.text = tmpName

        var tmpAddress = current.address
        if (tmpAddress.length > 13) {
            tmpAddress= tmpAddress.substring(IntRange(0, 10)) + "..."
        }
        holder.itemView.map_item_address.text = tmpAddress
        holder.itemView.map_item_distance.text = current.distance.toString()

        val p = ((current.current.toFloat() / current.total.toFloat()) * 100).roundToInt()
        holder.itemView.map_item_percent.text = "$p%"
        holder.itemView.map_item_progress.progress = p
    }

    internal fun setList(list: List<MapStoreModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}
