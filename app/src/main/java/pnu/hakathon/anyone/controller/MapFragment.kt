package pnu.hakathon.anyone.controller

import android.Manifest
import android.content.Context
import android.graphics.Color
import android.location.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.fragment_map.view.*
import net.daum.mf.map.api.MapCircle
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.map.MapListAdapter
import java.util.*


class MapFragment : Fragment() {
    lateinit var context: MainActivity
    lateinit var locationManager: LocationManager
    lateinit var mapView: MapView

    var currentMarker: MapPOIItem? = null
    var currentCircle: MapCircle? = null
    var currentLat: Double? = 35.23177955501981
    var currentLng: Double? = 129.08447619178358
    var currentAddress: String? = ""

    var addressTextView: TextView? = null

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
        addressTextView = v.map_current_address as TextView
        mapView = MapView(container?.context)

        setupMap(v, mapView)

        val adapter = MapListAdapter(context)
        v.map_recyclerview.adapter = adapter

        context.mapViewModel.list.observe(context, Observer {
            it?.let {
                adapter.setList(it)
            }
        })
        context.mapViewModel.setDummyData()

        v.map_my_location.setOnClickListener {
            getLocation()
        }
        return v
    }


    fun setupMap(v: View, mapView: MapView) {
        val mapPoint = MapPoint.mapPointWithGeoCoord(35.23177955501981, 129.08447619178358)
        mapView.setMapCenterPointAndZoomLevel(mapPoint, 3, true)

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

        mapView.addPOIItem(marker1)
        mapView.addPOIItem(marker2)
        mapView.addPOIItem(marker3)

        v.map_view.addView(mapView)
    }

    val mLocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            currentLat = location?.latitude
            currentLng = location?.longitude
            setCurrentLocation()
            addressTextView?.text = getCompleteAddressString(context, currentLat!!, currentLng!!)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }
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
        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                100, 1f, mLocationListener
            )
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                100, 1f, mLocationListener
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    fun setCurrentLocation() {
        val mapPoint = MapPoint.mapPointWithGeoCoord(currentLat!!, currentLng!!)
        mapView.setMapCenterPoint(mapPoint, true)
        mapView.setMapCenterPointAndZoomLevel(mapPoint, 4, true)
        mapView.zoomIn(true)

        removeCurrentMarker()
        removeCurrentCircle()
        setCurrentMarker(mapPoint)
        setCurrentCircle(mapPoint)
    }

    fun setCurrentMarker(mapPoint: MapPoint) {
        currentMarker = MapPOIItem()
        currentMarker?.let { marker ->
            marker.itemName = "Default marker"
            marker.tag = 0
            marker.mapPoint = mapPoint
            marker.markerType = MapPOIItem.MarkerType.CustomImage
            marker.customImageResourceId = R.drawable.custom_marker_current_image
            marker.isCustomImageAutoscale = false
            marker.setCustomImageAnchor(0.5f, 1.0f)
            mapView.addPOIItem(marker)
        }
    }

    fun removeCurrentMarker() {
        currentMarker?.let {
            mapView.removePOIItem(it)
        }
    }

    fun setCurrentCircle(mapPoint: MapPoint) {
        currentCircle =
            MapCircle(mapPoint, 500, Color.parseColor("#00000000"), Color.parseColor("#30000000"))
        currentCircle?.let { circle ->
            circle.tag = 4
            mapView.addCircle(circle)
        }
    }

    fun removeCurrentCircle() {
        currentCircle?.let {
            mapView.removeCircle(it)
        }
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
        strAdd = strAdd.substring(5)

        currentAddress = strAdd
        return strAdd
    }

    companion object {
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }
}