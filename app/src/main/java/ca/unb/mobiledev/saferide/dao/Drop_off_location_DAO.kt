package ca.unb.mobiledev.saferide.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.unb.mobiledev.saferide.entity.Dropofflocation
import ca.unb.mobiledev.saferide.entity.Stations

@Dao
interface Drop_off_location_DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dropOffLocation: Dropofflocation)

    @Query("SELECT * from drop_off_location where status = :status and station = :station ORDER BY create_at ASC LIMIT :seat")
    fun getNextPickup(status: Dropofflocation.Status, seat: Int, station: Stations): LiveData<List<Dropofflocation>>


}