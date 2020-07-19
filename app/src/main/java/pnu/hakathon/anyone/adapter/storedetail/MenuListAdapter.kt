package pnu.hakathon.anyone.adapter.storedetail

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_menu_item.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.model.MenuModel

class MenuListAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<MenuListAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<MenuModel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.detail_menu_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        Glide.with(context).load(current.imageURL).into(holder.itemView.detail_menu_image)
        holder.itemView.detail_menu_name.text = current.name
        holder.itemView.detail_menu_cost.text = current.cost + "원"

    }

    internal fun setMenu(menus: List<MenuModel>) {
        this.list = menus
        notifyDataSetChanged()
    }
}