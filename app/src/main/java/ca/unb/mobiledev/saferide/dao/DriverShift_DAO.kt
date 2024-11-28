package ca.unb.mobiledev.saferide.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.unb.mobiledev.saferide.entity.DriverShift

@Dao
interface DriverShift_DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(driverShift: DriverShift)

    @Query("Select * from driver_shift")
    fun listAllDrivers(): LiveData<List<DriverShift>>
}