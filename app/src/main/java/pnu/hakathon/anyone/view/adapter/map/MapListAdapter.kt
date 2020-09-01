package pnu.hakathon.anyone.view.adapter.map

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_map_list.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.entity.StoreModel
import kotlin.math.roundToInt

class MapListAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<MapListAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<StoreModel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_map_list, parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        Glide.with(context).load(
                current.imageURL
        ).placeholder(R.drawable.image_empty).into(holder.itemView.map_item_image)

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
        holder.itemView.map_item_empty_seat.text = "${current.total - current.current}"

        var fileName = "ic_battery_"
        if (p in 0..24){
            fileName += "1"
        } else if (p in 25..49) {
            fileName += "2"
        } else if (p in 50..74) {
            fileName += "3"
        } else if (p in 75..100) {
            fileName += "4"
        }
        Glide.with(context).load(context.resources.getIdentifier(fileName, "drawable", context.packageName))
            .into(holder.itemView.map_item_battery)
    }

    internal fun setList(list: List<StoreModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}
