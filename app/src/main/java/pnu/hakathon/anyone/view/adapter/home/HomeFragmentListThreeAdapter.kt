package pnu.hakathon.anyone.view.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_fragment_item3.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.model.HomeFragmentItemThree

class HomeFragmentListThreeAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<HomeFragmentListThreeAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var stores = emptyList<HomeFragmentItemThree>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflater.inflate(
                R.layout.home_fragment_item3,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = stores.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = stores[position]
        Glide.with(context).load(
            context.resources.getIdentifier(
                current.imageURL,
                "drawable",
                context.packageName
            )
        ).into(holder.itemView.home_fragment_item3_image)
        holder.itemView.home_fragment_item3_text1.text = current.address
        holder.itemView.home_fragment_item3_text2.text = current.name
    }

    internal fun setList(hs: List<HomeFragmentItemThree>) {
        this.stores = hs
        notifyDataSetChanged()
    }
}