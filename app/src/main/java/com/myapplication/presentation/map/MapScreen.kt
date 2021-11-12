package com.myapplication.presentation.map

import android.content.Context
import android.location.Criteria
import android.location.LocationManager
import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.google.android.libraries.maps.MapView
import com.google.maps.android.ktx.awaitMap
import com.myapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel()
) {
    val mapView = rememberMapViewWithLifeCycle()
    val scaffoldState = rememberScaffoldState()
    val locationManager =
        LocalContext.current.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val provider = locationManager.getBestProvider(Criteria(), true)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.padding(it)) {
            AndroidView(
                factory = { mapView }
            ) { mapView ->
                CoroutineScope(Dispatchers.Main).launch {
                    val map = mapView.awaitMap()
                    map.uiSettings.isZoomControlsEnabled = true
//                    map.isMyLocationEnabled = true
                }
            }
        }
    }
}

@Composable
fun rememberMapViewWithLifeCycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            id = R.id.map_frame
        }
    }
    val lifecycleObserver = rememberMapLifeCycleObserver(mapView)
    val lifeCycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifeCycle) {
        lifeCycle.addObserver(lifecycleObserver)
        onDispose {
            lifeCycle.removeObserver(lifecycleObserver)
        }
    }

    return mapView
}

@Composable
fun rememberMapLifeCycleObserver(mapView: MapView): LifecycleObserver =
    remember(mapView) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> throw IllegalStateException()
            }
        }
    }
