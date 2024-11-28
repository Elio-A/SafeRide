package ca.unb.mobiledev.saferide.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ca.unb.mobiledev.saferide.entity.Dropofflocation
import ca.unb.mobiledev.saferide.entity.Stations
import ca.unb.mobiledev.saferide.entity.User
import ca.unb.mobiledev.saferide.repository.DropOffLocationRepository

class DropOffLocationViewModel (private val repository: DropOffLocationRepository): ViewModel() {
    fun getNextPassengers(seat: Int, station: Stations): LiveData<List<Dropofflocation>>{
        return repository.getNextPassengers(seat, station)
    }

    fun insertDropOffLocation(user: User, station: Stations, location: String){
        repository.insertDropOffLocation(user, station, location)
    }

    fun deleteLocation(user: User){
        repository.deleteLocation(user)
    }
}