package ca.unb.mobiledev.saferide.DatabaseHelpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Station(
    var id: Int = 0,
    val name: String,
    val location: String
)

class StationHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "saferide.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_STATION = "stations"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_LOCATION = "location"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            create table $TABLE_STATION(
                $COLUMN_ID integer primary key autoincrement,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_LOCATION TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_STATION")
        onCreate(db)
    }

    fun addStation(name: String, location: String): Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_LOCATION, location)
        val result = db.insert(TABLE_STATION, null, values)
        db.close()
        return result
    }

    fun getStations(): List<Station>{
        val db = this.readableDatabase
        val query = "SELECT * from $TABLE_STATION"
        val cursor = db.rawQuery(query, null)

        var result = mutableListOf<Station>()

        if(cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val location = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION))

                val station = Station(id, name, location)
                result.add(station)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return result
    }

}