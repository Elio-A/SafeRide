package ca.unb.mobiledev.saferide.repository

import android.app.Application
import androidx.lifecycle.LiveData
import ca.unb.mobiledev.saferide.dao.Drop_off_location_DAO
import ca.unb.mobiledev.saferide.db.database
import ca.unb.mobiledev.saferide.db.database.Companion.getDatabase
import ca.unb.mobiledev.saferide.entity.Dropofflocation
import ca.unb.mobiledev.saferide.entity.Stations
import ca.unb.mobiledev.saferide.entity.User
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class DropOffLocationRepository(application: Application) {
    private val dropOffLocationDao: Drop_off_location_DAO = getDatabase(application).dropOffLocationDao()

    fun getNextPassengers(seat: Int, station: Stations): LiveData<List<Dropofflocation>> {
        return dropOffLocationDao.getNextPickup(Dropofflocation.Status.Waiting, seat, station)
    }

    fun insertDropOffLocation(user: User, station: Stations, location: String){
        val newDropofflocation = Dropofflocation()
        newDropofflocation.user = user
        newDropofflocation.location = location
        newDropofflocation.station = station
        insert(newDropofflocation)
    }

    fun deleteLocation(user: User): Int{
        val dataReadFuture: Future<Int> = database.databaseWriterExecutor.submit(
            Callable {
                dropOffLocationDao.deleteLocation(user)
            })
        return try {
            while (!dataReadFuture.isDone) {
                // Simulating another task
                TimeUnit.SECONDS.sleep(1)
            }
            dataReadFuture.get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
            0
        } catch (e: InterruptedException) {
            e.printStackTrace()
            0
        }
    }

    private fun insert(dropofflocation: Dropofflocation){
        database.databaseWriterExecutor.execute{ dropOffLocationDao.insert(dropofflocation) }
    }
}