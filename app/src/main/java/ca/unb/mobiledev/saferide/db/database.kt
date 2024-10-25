package ca.unb.mobiledev.saferide.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ca.unb.mobiledev.saferide.dao.*
import ca.unb.mobiledev.saferide.entity.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [DriverShift::class, Dropofflocation::class, User::class], version = 1, exportSchema = false)
abstract class database: RoomDatabase() {
    abstract fun driverShiftDao(): DriverShift_DAO
    abstract fun dropOffLocationDao(): Drop_off_location_DAO
    abstract fun userDao(): User_DAO
    //TODO add DAO for Vehicle and Stations

    companion object{
        @Volatile
        private var INSTANCE: database? = null
        private const val NUMBER_OF_THREADS = 4

        val databaseWriterExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): database{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
            val instance = Room.databaseBuilder(context.applicationContext,
                database::class.java, "app_database")
                .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}