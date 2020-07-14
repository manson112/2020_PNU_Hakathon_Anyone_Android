package pnu.hakathon.anyone.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_album_item.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.model.AlbumItem

class HomeAlbumListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<HomeAlbumListAdapter.HomeAlbumListViewHolder>() {
    private val mContext = context
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var albums = emptyList<AlbumItem>()

    inner class HomeAlbumListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAlbumListViewHolder {
        return HomeAlbumListViewHolder(inflater.inflate(R.layout.home_album_item, parent, false))
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: HomeAlbumListViewHolder, position: Int) {
        val current = albums[position]
        Glide.with(mContext).load(current.imageURL).into(holder.itemView.home_album_item_image)
        holder.itemView.home_album_item_text1.text = current.title
        holder.itemView.home_album_item_text2.text = current.subTitle
    }

    internal fun setAlbum(list: List<AlbumItem>) {
        this.albums = list
        notifyDataSetChanged()
    }
}