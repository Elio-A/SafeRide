package ca.unb.mobiledev.saferide.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ca.unb.mobiledev.saferide.entity.DriverShift

@Dao
interface DriverShift_DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(driverShift: DriverShift)
}