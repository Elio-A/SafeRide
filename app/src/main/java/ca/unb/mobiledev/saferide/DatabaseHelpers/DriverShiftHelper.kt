package ca.unb.mobiledev.saferide.DatabaseHelpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DriverShiftHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "saferide.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_SHIFT = "driverShift"
        private const val COLUMN_ID = "id"
        private const val COLUMN_DRIVER_ID = "driver_id"
        private const val COLUMN_STATION_ID = "station_id"
        private const val COLUMN_VEHICLE_ID = "vehicle_id"
        private const val COLUMN_FRONTVIEW = "front_view"
        private const val COLUMN_LEFTVIEW = "left_view"
        private const val COLUMN_BACKVIEW = "back_view"
        private const val COLUMN_RIGHTVIEW = "right_view"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            create table $TABLE_SHIFT(
                $COLUMN_ID integer primary key autoincrement,
                $COLUMN_DRIVER_ID integer NOT NULL,
                $COLUMN_STATION_ID integer NOT NULL,
                $COLUMN_VEHICLE_ID integer NOT NULL,
                $COLUMN_FRONTVIEW BLOB NOT NULL,
                $COLUMN_LEFTVIEW BLOB NOT NULL,
                $COLUMN_BACKVIEW BLOB NOT NULL,
                $COLUMN_RIGHTVIEW BLOB NOT NULL,
                FOREIGN KEY(driver_id) REFERENCES users(id),
                FOREIGN KEY(station_id) REFERENCES stations(id),
                FOREIGN KEY(vehicle_id) references vehicles(id)
               
            )
        """.trimIndent()

        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SHIFT")
        onCreate(db)
    }

    fun addShiftDriver(driver_id: Int, station_id: Int, vehicle_id: Int, frontView: ByteArray, leftView: ByteArray, backView: ByteArray, rightView: ByteArray): Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_DRIVER_ID, driver_id)
        values.put(COLUMN_STATION_ID, station_id)
        values.put(COLUMN_VEHICLE_ID, vehicle_id)
        values.put(COLUMN_FRONTVIEW, frontView)
        values.put(COLUMN_LEFTVIEW, leftView)
        values.put(COLUMN_BACKVIEW, backView)
        values.put(COLUMN_RIGHTVIEW, rightView)

        val result = db.insert(TABLE_SHIFT, null, values)
        db.close()
        return result
    }
}