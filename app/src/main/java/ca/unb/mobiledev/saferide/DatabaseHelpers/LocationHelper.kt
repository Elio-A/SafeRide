package ca.unb.mobiledev.saferide.DatabaseHelpers

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


data class Locations(
    val user: Int,
    val location: String,
)

class LocationHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)  {
    companion object{
        private const val DATABASE_NAME = "saferide.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_LOCATION = "location"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "user_id"
        private const val COLUMN_LOCATION = "location"
        private const val COLUMN_STATUS = "status"
        private const val COLUMN_STATION =  "station_id"
        private const val COLUMN_TIME = "entry_time"
        private val idsToUpdate = mutableListOf<Int>()
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            create table $TABLE_LOCATION (
                $COLUMN_ID integer primary key autoincrement,
                $COLUMN_USERNAME TEXT NOT NULL,
                $COLUMN_STATION TEXT NOT NULL,
                $COLUMN_STATUS TEXT DEFAULT 0,
                $COLUMN_LOCATION TEXT NOT NULL,
                $COLUMN_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
                FOREIGN KEY(user_id) REFERENCES users(id),
                FOREIGN KEY(station_id) REFERENCES stations(id))
            """.trimMargin()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_LOCATION")
        onCreate(db)
    }

    fun addLocation(user: String, station: String, location: String): Long{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val localTime = dateFormat.format(Date())

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USERNAME, user)
        values.put(COLUMN_STATION, station)
        values.put(COLUMN_LOCATION, location)
        values.put(COLUMN_TIME, localTime)
        val result = db.insert(TABLE_LOCATION, null, values)
        db.close()
        return result
    }

    fun getNextTrip(station: String, seat: Int): List<Locations> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val todayDate = dateFormat.format(Date())

        val db = this.readableDatabase
        val columns = arrayOf(COLUMN_USERNAME, COLUMN_LOCATION, COLUMN_ID)
        val selection = "strftime('%Y-%m-%d', $COLUMN_TIME) = ? AND $COLUMN_STATION = ? AND $COLUMN_STATUS = ?"
        val args = arrayOf(todayDate, station, "0")

        val cursor = db.query(
            TABLE_LOCATION,
            columns,
            selection,
            args,
            null,
            null,
            "$COLUMN_TIME ASC",
            seat.toString()
        )

        val result = mutableListOf<Locations>()
        idsToUpdate.clear()

        if(cursor.moveToFirst()) {
            do {
                val user = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USERNAME))
                val location = cursor.getString(cursor.getColumnIndexOrThrow("location"))
                val locationId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val temp = Locations(user, location)
                result.add(temp)
                idsToUpdate.add(locationId)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return result
    }

    fun completeTrip() {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_STATUS, "1")

        for (id in idsToUpdate) {
            Log.i(TAG, "id: $id")
            val whereClause = "$COLUMN_ID = ?"
            val whereArgs = arrayOf(id.toString())
            db.update(TABLE_LOCATION, contentValues, whereClause, whereArgs)
        }
    }


}