package pnu.hakathon.anyone.view.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_fragment_item1.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.model.HomeFragmentItemOne

class HomeFragmentListOneAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<HomeFragmentListOneAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<HomeFragmentItemOne>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflater.inflate(
                R.layout.home_fragment_item1,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        Glide.with(context).load(
            context.resources.getIdentifier(
                current.imageURL,
                "drawable",
                context.packageName
            )
        ).into(holder.itemView.home_fragment_item1_image)
        holder.itemView.home_fragment_item1_text.text = current.name
//        holder.itemView.home_recommend_item_seat.text = (current.total - current.current).toString()
    }

    fun setList(list: List<HomeFragmentItemOne>) {
        this.list = list
        notifyDataSetChanged()
    }
}