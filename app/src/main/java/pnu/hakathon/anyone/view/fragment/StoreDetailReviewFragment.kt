package pnu.hakathon.anyone.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pnu.hakathon.anyone.R

class StoreDetailReviewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_detail_review, container, false)
    }

    companion object {
        fun newInstance() =
            StoreDetailReviewFragment()
    }
}