package pnu.hakathon.anyone.view.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_fragment_item1.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.entity.StoreModel

class HomeFragmentHashListAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<HomeFragmentHashListAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<StoreModel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflater.inflate(
                R.layout.home_hash_fragment_item,
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        Glide.with(context).load(current.imageURL).placeholder(R.drawable.image_empty).into(holder.itemView.home_fragment_item1_image)
        holder.itemView.home_fragment_item1_text.text = current.storeName
    }

    fun setList(list: List<StoreModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}