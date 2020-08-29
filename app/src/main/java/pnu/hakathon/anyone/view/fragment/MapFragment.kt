package pnu.hakathon.anyone.view.fragment

import android.Manifest
import android.content.Context
import android.graphics.Color
import android.location.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.fragment_map.view.*
import net.daum.mf.map.api.MapCircle
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.view.activity.MainActivity
import pnu.hakathon.anyone.view.adapter.map.MapListAdapter
import pnu.hakathon.anyone.viewmodel.MapViewModel
import java.util.*


class MapFragment : Fragment() {
    private val mapViewModel: MapViewModel by stateViewModel()
    lateinit var context: MainActivity

    var locationManager: LocationManager? = null
    var currentMarker: MapPOIItem? = null
    var currentCircle: MapCircle? = null

    var researchContainer: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)
        context = activity as MainActivity
        checkPermission()
        researchContainer = v.map_research_container
        mapViewModel.categoryID = context.categoryID
        mapViewModel.getNewList()
        val mapView = MapView(container?.context)
        mapView.setMapViewEventListener(mapViewEventListener)

        setupMap(v, mapView)
        // List
        val adapter = MapListAdapter(context)
        v.map_recyclerview.adapter = adapter

        // Observers
        mapViewModel.list.observe(context, Observer {
            it?.let {
                adapter.setList(it)
                v.map_research_container.visibility = View.INVISIBLE
                mapView.removeAllPOIItems()

                for (i in it.indices) {
                    val mp = MapPoint.mapPointWithGeoCoord(it[i].lat, it[i].lng)
                    val marker = MapPOIItem()
                    marker.itemName = it[i].storeName
                    marker.tag = i + 1
                    marker.mapPoint = mp
                    marker.markerType = MapPOIItem.MarkerType.CustomImage
                    marker.customImageResourceId = R.drawable.custom_marker
                    marker.isCustomImageAutoscale = true
                    marker.setCustomImageAnchor(0.5f, 1.0f)

                    mapView.addPOIItem(marker)
                }

                if (it.isEmpty()) {
                    v.map_empty_text.visibility = View.VISIBLE
                } else {
                    v.map_empty_text.visibility = View.INVISIBLE
                }
            }
            currentMarker?.let { cur ->
                mapView.addPOIItem(cur)
            }
        })
        mapViewModel.lat?.observe(context, Observer {
            updateMap(mapView)
            mapViewModel.stopLoading()
        })
        mapViewModel.lng?.observe(context, Observer {
            updateMap(mapView)
            mapViewModel.stopLoading()
        })
        mapViewModel.currentAddress.observe(context, Observer {
            v.map_current_address.text = it
        })
        mapViewModel.isFindingLocation.observe(context, Observer {
            if (it) {
                v.map_find_location_progress.visibility = View.VISIBLE
            } else {
                v.map_find_location_progress.visibility = View.GONE
            }
        })

        v.map_research_container.setOnClickListener {
            val center = mapView.mapCenterPoint
            mapViewModel.lat?.value = center.mapPointGeoCoord.latitude
            mapViewModel.lng?.value = center.mapPointGeoCoord.longitude
            context.homeViewModel.setLatLng(center.mapPointGeoCoord.latitude, center.mapPointGeoCoord.longitude)
            mapViewModel.getNewList()
        }
        v.map_my_location.setOnClickListener {
            getLocation()
        }
        return v
    }

    val mapViewEventListener = object : MapView.MapViewEventListener {
        override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {
            Log.d("MAPFRAGMENT", "MAP VIEW CENTER POINT MOVED")
            researchContainer?.visibility = View.VISIBLE
        }
        override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {}
        override fun onMapViewInitialized(p0: MapView?) {}
        override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {}
        override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {}
        override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {}
        override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {}
        override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {}
        override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("lat", mapViewModel.getLat())
        outState.putDouble("lat", mapViewModel.getLng())
        super.onSaveInstanceState(outState)
    }

    fun setupMap(v: View, mapView: MapView) {
        val mapPoint = MapPoint.mapPointWithGeoCoord(35.23177955501981, 129.08447619178358)
        mapView.setMapCenterPointAndZoomLevel(mapPoint, 2, true)

        v.map_view.addView(mapView)
    }

    private fun updateMap(mapView: MapView) {
        val mapPoint = mapViewModel.getCenterMapPoint()
        changeCenter(mapView, mapPoint)
        removeCurrentMarker(mapView)
        removeCurrentCircle(mapView)
        setCurrentMarker(mapView, mapPoint)
        setCurrentCircle(mapView, mapPoint)
    }

    private fun checkPermission() {
        TedPermission.with(context)
            .setPermissionListener(permissionListener)
            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .check()
    }

    val permissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            getLocation()
        }
        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            context.finish()
        }
    }

    fun getLocation() {
        mapViewModel.needUpdate()
        mapViewModel.startLoading()
        try {
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                100, 1f, mLocationListener
            )
            locationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                100, 1f, mLocationListener
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
            mapViewModel.stopLoading()
        }
    }

    val mLocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            if (mapViewModel.needToUpdate) {
                mapViewModel.lat?.value = location?.latitude
                mapViewModel.lng?.value = location?.longitude
                getCompleteAddressString(
                    context,
                    mapViewModel.lat!!.value!!,
                    mapViewModel.lng!!.value!!
                )
                mapViewModel.getNewList()
                context.homeViewModel.setLatLng(location?.latitude!!, location.longitude)
            }
        }
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        override fun onProviderEnabled(provider: String?) {}
        override fun onProviderDisabled(provider: String?) {}
    }

    fun getCompleteAddressString(
        context: Context?,
        LATITUDE: Double,
        LONGITUDE: Double
    ): String? {
        var strAdd = ""
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses: List<Address>? =
                geocoder.getFromLocation(LATITUDE, LONGITUDE, 1)
            if (addresses != null) {
                val returnedAddress: Address = addresses[0]
                val strReturnedAddress = StringBuilder("")
                for (i in 0..returnedAddress.getMaxAddressLineIndex()) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n")
                }
                strAdd = strReturnedAddress.toString()
                Log.w("MyCurrentloctionaddress", strReturnedAddress.toString())
            } else {
                Log.w("MyCurrentloctionaddress", "No Address returned!")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.w("MyCurrentloctionaddress", "Canont get Address!")
        }

        // "대한민국 " 글자 지워버림
        if (strAdd.length >= 6) {
            strAdd = strAdd.substring(5)
        }
        mapViewModel.currentAddress.value = strAdd
        return strAdd
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

    fun removeCurrentMarker(mapView: MapView) {
        currentMarker?.let {
            mapView.removePOIItem(it)
        }
    }

    fun setCurrentCircle(mapView: MapView, mapPoint: MapPoint) {
        currentCircle =
            MapCircle(mapPoint, 400, Color.parseColor("#00000000"), Color.parseColor("#30000000"))
        currentCircle?.let { circle ->
            circle.tag = 100
            mapView.addCircle(circle)
        }
    }

    fun removeCurrentCircle(mapView: MapView) {
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