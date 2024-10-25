package ca.unb.mobiledev.saferide.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User {

    @PrimaryKey
    var user_id = 0
    var firstName: String? = null
    var lastName: String? = null
    var email: String? = null
    var driver: Boolean = false
}