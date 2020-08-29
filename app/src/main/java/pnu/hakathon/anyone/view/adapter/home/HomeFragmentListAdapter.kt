package pnu.hakathon.anyone.view.adapter.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_fragment_item2.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.entity.NearStore

class HomeFragmentListAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<HomeFragmentListAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var stores = emptyList<NearStore>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("HOMELISTTWO", "view holder is created")
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
        Log.d("HOMELISTTWO", current.storeName)
        Glide.with(context).load(current.imageURL).into(holder.itemView.home_fragment_item2_image)
        Log.d("HOMELISTTWO", current.storeName)
        holder.itemView.home_fragment_item2_text.text = current.storeName
        holder.itemView.home_fragment_item2_seat.text = (current.total - current.current).toString()
        holder.itemView.setOnClickListener {
            Log.d("HOMELISTTWO", "POSITION:$position is clicked")
        }
    }

    internal fun setList(hs: List<NearStore>) {
        Log.d("HOMELISTTWO", "setList")
        this.stores = hs
        notifyDataSetChanged()
    }
}