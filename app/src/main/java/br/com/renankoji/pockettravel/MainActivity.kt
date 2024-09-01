package br.com.renankoji.pockettravel

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.renankoji.pockettravel.screens.CityScreen
import br.com.renankoji.pockettravel.screens.DashboardScreen
import br.com.renankoji.pockettravel.ui.theme.PocketTravelTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            PocketTravelTheme {
                var userLatitude by remember { mutableStateOf<Double?>(null) }
                var userLongitude by remember { mutableStateOf<Double?>(null) }
                var hasLocationPermission by remember { mutableStateOf(false) }

                // Verificar permissões e buscar localização se permitido
                LaunchedEffect(Unit) {
                    hasLocationPermission = checkLocationPermissionAndFetch(
                        onLocationReceived = { latitude, longitude ->
                            userLatitude = latitude
                            userLongitude = longitude
                        }
                    )
                }

                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "dashboard") {
                        composable("dashboard") {
                            DashboardScreen(
                                userLatitude = userLatitude,
                                userLongitude = userLongitude,
                                hasLocationPermission = hasLocationPermission,
                                navController = navController
                            )
                        }
                        composable("city/{cityName}/{categoryOption}") { backStackEntry ->
                            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
                            val categoryOption = backStackEntry.arguments?.getString("categoryOption") ?: ""
                            CityScreen(
                                city = cityName,
                                category = categoryOption,
                                userLatitude = userLatitude,
                                userLongitude = userLongitude,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun checkLocationPermissionAndFetch(onLocationReceived: (Double?, Double?) -> Unit): Boolean {
        return if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                getLastKnownLocation(onLocationReceived)
            } catch (e: SecurityException) {
                Toast.makeText(this, "Erro de segurança ao acessar a localização.", Toast.LENGTH_SHORT).show()
                onLocationReceived(null, null)
            }
            true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            false
        }
    }

    private fun getLastKnownLocation(onLocationReceived: (Double?, Double?) -> Unit) {
        try {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        onLocationReceived(location.latitude, location.longitude)
                    } else {
                        Toast.makeText(
                            this,
                            "Não foi possível obter a localização.",
                            Toast.LENGTH_SHORT
                        ).show()
                        onLocationReceived(null, null)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Erro ao tentar obter a localização.", Toast.LENGTH_SHORT)
                        .show()
                    onLocationReceived(null, null)
                }
        } catch (e: SecurityException) {
            Toast.makeText(this, "Erro de segurança ao acessar a localização.", Toast.LENGTH_SHORT).show()
            onLocationReceived(null, null)
        }
    }
}
