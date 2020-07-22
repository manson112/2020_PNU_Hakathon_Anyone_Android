package pnu.hakathon.anyone.controller

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_map.view.*
import net.daum.mf.map.api.MapCircle
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.map.MapListAdapter

class MapFragment : Fragment() {
    lateinit var context: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)
        context = activity as MainActivity

        val mapView = MapView(container?.context)

        setupMap(v, mapView)

        val adapter = MapListAdapter(context)
        v.map_recyclerview.adapter = adapter

        context.mapViewModel.list.observe(context, Observer {
            it?.let {
                adapter.setList(it)
            }
        })

        context.mapViewModel.setDummyData()

        return v
    }

    fun setupMap(v: View, mapView: MapView) {
        val mapPoint = MapPoint.mapPointWithGeoCoord(35.23177955501981, 129.08447619178358)
        mapView.setMapCenterPointAndZoomLevel(mapPoint, 3, true)
        val marker = MapPOIItem()
        marker.itemName = "Default marker"
        marker.tag = 0
        marker.mapPoint = mapPoint
        marker.markerType = MapPOIItem.MarkerType.CustomImage
        marker.customImageResourceId = R.drawable.custom_marker_current_image
        marker.isCustomImageAutoscale = false
        marker.setCustomImageAnchor(0.5f, 1.0f)

        val mp1 = MapPoint.mapPointWithGeoCoord(35.23224699968794, 129.08454306909528)
        val mp2 = MapPoint.mapPointWithGeoCoord(35.233351670722854, 129.0850216495288)
        val mp3 = MapPoint.mapPointWithGeoCoord(35.23314214085866, 129.08476092829864)
        val marker1 = MapPOIItem()
        val marker2 = MapPOIItem()
        val marker3 = MapPOIItem()
        marker1.itemName = "유달리"
        marker1.tag = 1
        marker1.mapPoint = mp1
        marker1.markerType = MapPOIItem.MarkerType.CustomImage
        marker1.customImageResourceId = R.drawable.custom_marker
        marker1.isCustomImageAutoscale = false
        marker1.setCustomImageAnchor(0.5f, 1.0f)

        marker2.itemName = "더 벤티"
        marker2.tag = 2
        marker2.mapPoint = mp2
        marker2.markerType = MapPOIItem.MarkerType.CustomImage
        marker2.customImageResourceId = R.drawable.custom_marker
        marker2.isCustomImageAutoscale = false
        marker2.setCustomImageAnchor(0.5f, 1.0f)

        marker3.itemName = "팔공티"
        marker3.tag = 3
        marker3.mapPoint = mp3
        marker3.markerType = MapPOIItem.MarkerType.CustomImage
        marker3.customImageResourceId = R.drawable.custom_marker
        marker3.isCustomImageAutoscale = false
        marker3.setCustomImageAnchor(0.5f, 1.0f)

        val circle =
            MapCircle(mapPoint, 500, Color.parseColor("#00000000"), Color.parseColor("#30000000"))
        circle.tag = 4

        mapView.addPOIItem(marker)
        mapView.addPOIItem(marker1)
        mapView.addPOIItem(marker2)
        mapView.addPOIItem(marker3)
        mapView.addCircle(circle)

        v.map_view.addView(mapView)
    }

    companion object {
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }
}