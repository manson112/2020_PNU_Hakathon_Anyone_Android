package pnu.hakathon.anyone.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_fragment_item2.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.model.HomeFragmentItemTwo

class HomeFragmentListTwoAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<HomeFragmentListTwoAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var stores = emptyList<HomeFragmentItemTwo>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflater.inflate(
                R.layout.home_fragment_item2,
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
        ).into(holder.itemView.home_fragment_item2_image)
        holder.itemView.home_fragment_item2_text.text = current.name
        holder.itemView.home_fragment_item2_seat.text = (current.total - current.current).toString()

    }

    internal fun setList(hs: List<HomeFragmentItemTwo>) {
        this.stores = hs
        notifyDataSetChanged()
    }
}