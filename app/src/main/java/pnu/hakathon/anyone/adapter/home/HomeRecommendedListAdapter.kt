package pnu.hakathon.anyone.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_recommend_item.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.model.RecommendedStore

class HomeRecommendedListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<HomeRecommendedListAdapter.HomeRecommendedViewHolder>() {

    private val mContext = context
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var recommendedList = emptyList<RecommendedStore>()

    inner class HomeRecommendedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecommendedViewHolder {
        return HomeRecommendedViewHolder(
            inflater.inflate(
                R.layout.home_recommend_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = recommendedList.size

    override fun onBindViewHolder(holder: HomeRecommendedViewHolder, position: Int) {
        val current = recommendedList[position]
        Glide.with(mContext).load(current.imageURL).into(holder.itemView.home_recommend_item_image)
        holder.itemView.home_recommend_item_text.text = current.name
        holder.itemView.home_recommend_item_seat.text = (current.total - current.current).toString()
    }

    fun setRecommended(list: List<RecommendedStore>) {
        this.recommendedList = list
        notifyDataSetChanged()
    }
}