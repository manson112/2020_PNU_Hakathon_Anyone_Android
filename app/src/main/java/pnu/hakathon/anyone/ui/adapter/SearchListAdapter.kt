package pnu.hakathon.anyone.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_search.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.ui.activity.StoreDetailActivity

class SearchListAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<StoreModel>()
    private var onClickedTime = System.currentTimeMillis()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_search, parent, false)).apply {
            itemView.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
                val currentTime = System.currentTimeMillis()
                if (currentTime - onClickedTime > itemView.search_item_transformationLayout.duration) {
                    StoreDetailActivity.startActivity(
                        itemView.search_item_transformationLayout,
                        list[position]
                    )
                    onClickedTime = currentTime
                }
            }
        }
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        Glide.with(context).load(current.imageURL).placeholder(R.drawable.image_empty).into(holder.itemView.search_item_image)
        holder.itemView.search_item_name.text = current.storeName
//        holder.itemView.search_item_hashtag.text = current.hashTag
        holder.itemView.search_item_address.text = current.address
    }

    internal fun setList(list: List<StoreModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}
