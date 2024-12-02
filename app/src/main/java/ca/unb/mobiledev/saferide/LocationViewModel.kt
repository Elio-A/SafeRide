package ca.unb.mobiledev.saferide

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ca.unb.mobiledev.saferide.DatabaseHelpers.LocationHelper
import ca.unb.mobiledev.saferide.DatabaseHelpers.Locations
import kotlinx.coroutines.launch

class LocationViewModel(application: Application): AndroidViewModel(application) {
    private val locationHelper: LocationHelper = LocationHelper(application)
    private val _locationLiveData = MutableLiveData<List<Locations>>()
    val locationLiveData: LiveData<List<Locations>> = _locationLiveData

    fun getLocationForStation(station: String, seat: Int){
        viewModelScope.launch {
            val locations = locationHelper.getNextTrip(station, seat)
            _locationLiveData.postValue(locations)
        }
    }
}
