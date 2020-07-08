package pnu.hakathon.anyone.adapter.bookmark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.model.RecommendedStore

class RecommendedStoreListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<RecommendedStoreListAdapter.RecommendedStoreViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var recommendedStores = emptyList<RecommendedStore>()

    inner class RecommendedStoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendedStoreListAdapter.RecommendedStoreViewHolder {
        return RecommendedStoreViewHolder(inflater.inflate(R.layout.bookmark_item, parent, false))
    }

    override fun getItemCount() = recommendedStores.size

    override fun onBindViewHolder(
        holder: RecommendedStoreListAdapter.RecommendedStoreViewHolder,
        position: Int
    ) {
        val current = recommendedStores[position]
//        holder.itemView.bookmark_textview.text = "${current.id} ${current.name}"
    }

    internal fun setRecommendedStores(rs: List<RecommendedStore>) {
        this.recommendedStores = rs
        notifyDataSetChanged()
    }

}