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
    val user: User? = null
    val station: String? = null
    val status: Status = Status.Waiting
    @ColumnInfo(name = "create_at", defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: Long = 0
}