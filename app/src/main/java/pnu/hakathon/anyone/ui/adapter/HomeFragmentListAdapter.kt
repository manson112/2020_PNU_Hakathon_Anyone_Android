package pnu.hakathon.anyone.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_home_list.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.ui.activity.StoreDetailActivity

class HomeFragmentListAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<HomeFragmentListAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var stores = emptyList<StoreModel>()
    private var onClickedTime = System.currentTimeMillis()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflater.inflate(
                R.layout.item_home_list,
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
                val currentTime = System.currentTimeMillis()
                if (currentTime - onClickedTime > itemView.home_fragment_item_transformationLayout.duration) {
                    StoreDetailActivity.startActivity(
                        itemView.home_fragment_item_transformationLayout,
                        stores[position]
                    )
                    onClickedTime = currentTime
                }
            }
        }
    }

    override fun getItemCount(): Int = stores.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = stores[position]
        Glide.with(context).load(current.imageURL).placeholder(R.drawable.image_empty).into(holder.itemView.home_fragment_item2_image)
        holder.itemView.home_fragment_item2_text.text = current.storeName
        holder.itemView.home_fragment_item2_address.text = current.address

        val percent = (current.current / current.total.toDouble() * 100).toInt()
        holder.itemView.home_fragment_item_battery_percent.text = "$percent%"
    }

    internal fun setList(ns: List<StoreModel>) {
        this.stores = ns
        notifyDataSetChanged()
    }
}