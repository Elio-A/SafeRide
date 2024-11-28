package ca.unb.mobiledev.saferide.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "drop_off_location")
class Dropofflocation {

    enum class Status {
        Waiting,
        Completed,
    }

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
    var user: User? = null
    var station: Stations? = null
    val status: Status = Status.Waiting
    var location: String? = null
    @ColumnInfo(name = "create_at", defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: Long = 0
}