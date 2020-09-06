package pnu.hakathon.anyone.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.home_viewtype_1.view.*
import kotlinx.android.synthetic.main.home_viewtype_2.view.*
import kotlinx.android.synthetic.main.home_viewtype_3.view.*
import kotlinx.android.synthetic.main.home_viewtype_4.view.*
import kotlinx.android.synthetic.main.home_viewtype_5.view.*
import kotlinx.android.synthetic.main.item_home_list.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.ui.activity.SearchActivity
import pnu.hakathon.anyone.ui.activity.StoreDetailActivity

class HomeFragmentListAdapter internal constructor(
    val context: Context, val categoryID: String, val categoryName: String, val frag: Fragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var stores = emptyList<StoreModel>()
    private var onClickedTime = System.currentTimeMillis()
    private var seats = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ViewTypeOne(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ViewTypeTwo(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ViewTypeThree(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ViewTypeFour(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ViewTypeFive(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            0 -> {
                return ViewTypeOne(inflater.inflate(R.layout.home_viewtype_1, parent, false)).apply {
                    Glide.with(context)
                    .load("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png")
                    .into(itemView.home_profile_image)
                }
            }
            1 -> {
                return ViewTypeTwo(inflater.inflate(R.layout.home_viewtype_2, parent, false)).apply {
                    itemView.home_searchbar.hint = "$categoryName 이름을 검색해보세요"
                    itemView.home_searchbar.setOnClickListener {
                        context.startActivity(Intent(context, SearchActivity::class.java).putExtra("categoryID", categoryID))
                    }
                }
            }
            2 -> {
                return ViewTypeThree(inflater.inflate(R.layout.home_viewtype_3, parent, false)).apply {
                    itemView.home_text_category_1.text = categoryName
                    itemView.home_text_category_2.text = categoryName
                }
            }
            3 -> {
                return ViewTypeFour(inflater.inflate(R.layout.home_viewtype_4, parent, false)).apply {
                    val tabAdapter = TabAdapter(frag)
                    itemView.home_hash_pager.adapter = tabAdapter
                    TabLayoutMediator(itemView.home_tablayout, itemView.home_hash_pager) { tab, position ->
                        when(position) {
                            0 -> tab.text = "#조용한"
                            1 -> tab.text = "#쾌적한"
                            2 -> tab.text = "#활기찬"
                            3 -> tab.text = "#친절한"
                        }
                    }.attach()
                    itemView.home_hash_pager.isUserInputEnabled = false
                }
            }
            4 -> {
                return ViewTypeFive(inflater.inflate(R.layout.home_viewtype_5, parent, false)).apply {
                    itemView.home_text_category_3.text = categoryName
                }
            }
            5 -> {
                return ViewHolder(
                    inflater.inflate(
                        R.layout.item_home_list,
                        parent,
                        false
                    )
                ).apply {
                    itemView.setOnClickListener {
                        val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
                        val currentTime = System.currentTimeMillis()
                        if (currentTime - onClickedTime > itemView.home_fragment_item_transformationLayout.duration) {
                            StoreDetailActivity.startActivity(
                                itemView.home_fragment_item_transformationLayout,
                                stores[position]
                            )
                            onClickedTime = currentTime
                        }
                    }
                }
            }
            else -> {
                return ViewHolder(
                    inflater.inflate(
                        R.layout.item_home_list,
                        parent,
                        false
                    )
                )
            }
        }
    }
    override fun getItemCount(): Int = stores.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewTypeOne) {
            holder.itemView.home_text_num_of_seat.text = seats.toString()
        } else if (holder is ViewHolder) {
            val current = stores[position]
            Glide.with(context).load(current.imageURL).placeholder(R.drawable.image_empty).into(holder.itemView.home_fragment_item2_image)
            holder.itemView.home_fragment_item2_text.text = current.storeName
            holder.itemView.home_fragment_item2_address.text = current.address

            val percent = (current.current / current.total.toDouble() * 100).toInt()
            holder.itemView.home_fragment_item_battery_percent.text = "$percent%"
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position < 5) {
            return position
        }
        return 5
    }

    internal fun setList(ns: List<StoreModel>) {
        val arr = arrayListOf<StoreModel>()
        for (i in 0 until 5) { arr.add(0, StoreModel()) }
        arr.addAll(ns)
        this.stores = arr

        var seats = 0
        ns.forEach { item -> seats += (item.total - item.current) }

        notifyItemChanged(4)
        notifyItemRangeChanged(5, itemCount)
    }
}