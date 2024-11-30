package ca.unb.mobiledev.saferide.repository

import android.app.Application
import ca.unb.mobiledev.saferide.dao.DriverShift_DAO
import ca.unb.mobiledev.saferide.db.AppDatabase
import ca.unb.mobiledev.saferide.db.AppDatabase.Companion.getDatabase
import ca.unb.mobiledev.saferide.entity.DriverShift
import ca.unb.mobiledev.saferide.entity.Stations
import ca.unb.mobiledev.saferide.entity.Vehicle

class DriverShiftRepository(application: Application) {
    private val driverDao: DriverShift_DAO = getDatabase(application).driverShiftDao()

    fun insertDriverShift(driver_id: Int,
                          station: Stations,
                          vehicle: Vehicle,
                          frontView: ByteArray,
                          backView: ByteArray,
                          leftView: ByteArray,
                          rightView: ByteArray){
        val newDriverShift = DriverShift()
        newDriverShift.driver_id = driver_id
        newDriverShift.station = station
        newDriverShift.vehicle = vehicle
        newDriverShift.frontView = frontView
        newDriverShift.backView = backView
        newDriverShift.leftView = leftView
        newDriverShift.rightView = rightView
        insert(newDriverShift)

    }
    private fun insert(driverShift: DriverShift){
        AppDatabase.databaseWriterExecutor.execute{ driverDao.insert(driverShift) }
    }

}