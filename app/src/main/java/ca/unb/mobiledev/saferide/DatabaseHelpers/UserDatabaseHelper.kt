package ca.unb.mobiledev.saferide

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class UserDatabaseHelper(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{

    companion object {
        private const val DATABASE_NAME = "signupDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
        private const val IS_DRIVER = "isdriver"
    }

    override fun onCreate(db: SQLiteDatabase){
        val createTableQuery = """
            create table $TABLE_USERS (
                $COLUMN_ID integer primary key autoincrement,
                $COLUMN_USERNAME TEXT UNIQUE,
                $COLUMN_PASSWORD TEXT,
                $IS_DRIVER integer
            )
            """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(username: String, password: String, isDriver: Boolean): Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USERNAME, username)
        values.put(COLUMN_PASSWORD, password)
        values.put(IS_DRIVER, if (isDriver) 1 else 0)
        val result = db.insert(TABLE_USERS, null, values)
        db.close()
        Log.d("DatabaseHelper", "Insert result: $result")
        return result
    }

    fun checkUser(username: String, password: String): Pair<Boolean, Boolean>{
        val db = this.readableDatabase
        val query = "select * from $TABLE_USERS where $COLUMN_USERNAME = ? and $COLUMN_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(username, password))
        val userExists = cursor.count > 0
        var isDriver = false

        if(userExists){
            cursor.moveToFirst()
            val driverColumnIndex = cursor.getColumnIndex(IS_DRIVER)
            if(driverColumnIndex != -1){
                isDriver = cursor.getInt(driverColumnIndex) == 1
            }
        }
        cursor.close()
        db.close()
        return Pair(userExists, isDriver)
    }
}