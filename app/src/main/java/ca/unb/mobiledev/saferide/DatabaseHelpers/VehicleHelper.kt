package ca.unb.mobiledev.saferide.DatabaseHelpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Vehicle(
    var id: Int = 0,
    val make: String,
    val model: String,
    val year: Int,
    val seat: Int
)

class VehicleHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "saferide.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_VEHICLE = "vehicles"
        private const val COLUMN_ID = "id"
        private const val COLUMN_MAKE = "make"
        private const val COLUMN_MODEL = "model"
        private const val COLUMN_YEAR = "year"
        private const val COLUMN_SEAT = "seat"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            create table $TABLE_VEHICLE(
                $COLUMN_ID integer primary key autoincrement,
                $COLUMN_MAKE TEXT NOT NULL,
                $COLUMN_MODEL TEXT NOT NULL,
                $COLUMN_YEAR integer NOT NULL,
                $COLUMN_SEAT integer NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_VEHICLE")
        onCreate(db)
    }

    fun addVehicle(make: String, model: String, year: Int, seat: Int): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_MAKE, make)
        values.put(COLUMN_MODEL, model)
        values.put(COLUMN_YEAR, year)
        values.put(COLUMN_SEAT, seat)
        val result = db.insert(TABLE_VEHICLE, null, values)
        db.close()
        return result
    }

    fun getVehicles(): List<Vehicle>{
        val db = this.readableDatabase
        val query = "SELECT * from $TABLE_VEHICLE"
        val cursor = db.rawQuery(query, null)

        var result = mutableListOf<Vehicle>()

        if(cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val make = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MAKE))
                val model = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MODEL))
                val year = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_YEAR))
                val seat = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SEAT))

                val vehicle = Vehicle(id, make, model, year, seat)
                result.add(vehicle)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return result
    }
}