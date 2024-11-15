package ca.unb.mobiledev.saferide.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (

    @PrimaryKey @ColumnInfo (name = "user_id") var userId: Int,
    @ColumnInfo (name = "firstName") var firstName: String?,
    @ColumnInfo (name = "lastName") var lastName: String?,
    @ColumnInfo (name = "email") var email: String?,
    @ColumnInfo (name = "password") var password: String?,
    @ColumnInfo (name = "driver") var driver: Boolean,
)