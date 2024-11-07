package ca.unb.mobiledev.saferide.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (

    @PrimaryKey @ColumnInfo (name = "user_id") val userId: Int,
    @ColumnInfo (name = "firstName") val firstName: String?,
    @ColumnInfo (name = "lastName") val lastName: String?,
    @ColumnInfo (name = "email") val email: String?,
    @ColumnInfo (name = "driver") val driver: Boolean,
)