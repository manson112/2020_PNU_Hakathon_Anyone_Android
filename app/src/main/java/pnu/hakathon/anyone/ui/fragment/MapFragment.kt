package pnu.hakathon.anyone.ui.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_map.view.*
import net.daum.mf.map.api.MapCircle
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.ui.adapter.MapListAdapter
import pnu.hakathon.anyone.viewmodel.Loc
import pnu.hakathon.anyone.viewmodel.MainViewModel
import pnu.hakathon.anyone.viewmodel.MapViewModel
import timber.log.Timber


class MapFragment : Fragment() {
    private val mainViewModel by sharedViewModel<MainViewModel>()
    private val mapViewModel: MapViewModel by stateViewModel()

    var mapView: MapView? = null

    var currentMarker: MapPOIItem? = null
    var currentCircle: MapCircle? = null

    var researchContainer: LinearLayout? = null
    var sl: NestedScrollView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapViewModel.categoryID = mainViewModel.categoryID
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)
        researchContainer   = v.map_research_container
        sl                  = v.map_scroll_view

        setupMap(v, container?.context!!)

        if (mainViewModel.locationUpdated.value!!) {
            mapViewModel.setLatLng(mainViewModel.curLoc.value!!)
//            val mapPoint = mapViewModel.getCenterMapPoint()
//            mapView?.setMapCenterPointAndZoomLevel(mapPoint, 2, true)
            updateMap(true)
        } else {
            mainViewModel.locationUpdated.observe(viewLifecycleOwner, Observer {
                if (it) {
                    mapViewModel.setLatLng(mainViewModel.curLoc.value!!)
//                    val mapPoint = mapViewModel.getCenterMapPoint()
//                    mapView?.setMapCenterPointAndZoomLevel(mapPoint, 2, true)
                    updateMap(true)
                    mainViewModel.locationUpdated.removeObservers(viewLifecycleOwner)
                }
            })
        }

        // List
        val adapter =
            MapListAdapter(container.context!!)
        v.map_recyclerview.adapter = adapter

        // Observers
        mapViewModel.list.observe(viewLifecycleOwner, Observer {
            Timber.d("LIST HAS BEEN UPDATED")

            v.map_empty_text.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE

            it?.let {
                adapter.setList(it)
                v.map_research_container.visibility = View.INVISIBLE
                mapView?.removeAllPOIItems()

                for (i in it.indices) {
                    Timber.d("${it[i].storeName} / ${it[i].distance}")
                    val mp = MapPoint.mapPointWithGeoCoord(it[i].lat, it[i].lng)
                    val marker = MapPOIItem()
                    marker.itemName = it[i].storeName
                    marker.tag = i + 1
                    marker.mapPoint = mp
                    marker.markerType = MapPOIItem.MarkerType.CustomImage
                    marker.customImageResourceId = R.drawable.custom_marker
                    marker.isCustomImageAutoscale = true
                    marker.setCustomImageAnchor(0.5f, 1.0f)
                    mapView?.addPOIItem(marker)
                }

                if (it.isEmpty()) {
                    v.map_empty_text.visibility = View.VISIBLE
                } else {
                    v.map_empty_text.visibility = View.GONE
                }
            }
            currentMarker?.let { cur ->
                mapView?.addPOIItem(cur)
            }
        })

        mapViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    v.map_list_shimmer.startShimmer()
                } else {
                    v.map_list_shimmer.stopShimmer()
                }
                v.map_list_shimmer.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        v.map_research_container.setOnClickListener {
            mapView?.let {
                val center = it.mapCenterPoint
                mapViewModel.setLatLng(Loc(center.mapPointGeoCoord.latitude, center.mapPointGeoCoord.longitude))
                updateMap(false)
                v.map_research_container.visibility = View.GONE
            }
        }
        return v
    }

    val mapViewEventListener = object : MapView.MapViewEventListener {
        override fun onMapViewInitialized(p0: MapView?) {}
        override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {
            researchContainer?.visibility = View.VISIBLE
            sl?.requestDisallowInterceptTouchEvent(true)
        }
        override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {
            sl?.requestDisallowInterceptTouchEvent(true)
        }
        override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {
            sl?.requestDisallowInterceptTouchEvent(true)
        }
        override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {
            sl?.requestDisallowInterceptTouchEvent(true)
        }
        override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {
            sl?.requestDisallowInterceptTouchEvent(true)
        }
        override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {
            sl?.requestDisallowInterceptTouchEvent(true)
        }
        override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {
            sl?.requestDisallowInterceptTouchEvent(true)
        }
        override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {
            sl?.requestDisallowInterceptTouchEvent(true)
        }
    }


    private fun setupMap(v: View, context: Context) {
        mapView = MapView(context)
        mapView?.setMapViewEventListener(mapViewEventListener)
        v.map_view.addView(mapView)
    }

    private fun updateMap(isFirst: Boolean) {
        mapView?.let {
            val mapPoint = mapViewModel.getCenterMapPoint()
            changeCenter(it, mapPoint)
            if (isFirst) {
                removeCurrentMarker(it)
                setCurrentMarker(it, mapPoint)
            }
            removeCurrentCircle(it)
            setCurrentCircle(it, mapPoint)
        }
    }

    fun changeCenter(mapView: MapView, mapPoint: MapPoint) {
        mapView.setMapCenterPoint(mapPoint, true)
        mapView.setMapCenterPointAndZoomLevel(mapPoint, 3, true)
        mapView.zoomIn(true)
    }

    fun setCurrentMarker(mapView: MapView, mapPoint: MapPoint) {
        currentMarker = MapPOIItem()
        currentMarker?.let { marker ->
            marker.itemName = "내 위"
            marker.tag = 0
            marker.mapPoint = mapPoint
            marker.markerType = MapPOIItem.MarkerType.CustomImage
            marker.customImageResourceId = R.drawable.custom_marker_current_image
            marker.isCustomImageAutoscale = true
            marker.setCustomImageAnchor(0.5f, 1.0f)
            mapView.addPOIItem(marker)
        }
    }

    private fun removeCurrentMarker(mapView: MapView) {
        currentMarker?.let {
            mapView.removePOIItem(it)
        }
    }

    private fun setCurrentCircle(mapView: MapView, mapPoint: MapPoint) {
        currentCircle =
            MapCircle(mapPoint, 400, Color.parseColor("#00000000"), Color.parseColor("#30000000"))
        currentCircle?.let { circle ->
            circle.tag = 100
            mapView.addCircle(circle)
        }
    }

    private fun removeCurrentCircle(mapView: MapView) {
        currentCircle?.let {
            mapView.removeCircle(it)
        }
    }


    companion object {
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }
}