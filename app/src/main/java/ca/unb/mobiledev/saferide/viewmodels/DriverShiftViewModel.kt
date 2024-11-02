package ca.unb.mobiledev.saferide.viewmodels

import ca.unb.mobiledev.saferide.entity.Stations
import ca.unb.mobiledev.saferide.entity.Vehicle
import ca.unb.mobiledev.saferide.repository.DriverShiftRepository

class DriverShiftViewModel (private val repository: DriverShiftRepository) {
    fun insert(driver_id: Int,
               station: Stations,
               vehicle: Vehicle,
               frontView: ByteArray,
               backView: ByteArray,
               leftView: ByteArray,
               rightView: ByteArray){
        repository.insertDriverShift(driver_id, station, vehicle, frontView, backView, leftView, rightView)
    }
}