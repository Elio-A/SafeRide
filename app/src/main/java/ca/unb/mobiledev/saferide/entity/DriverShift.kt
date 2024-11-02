package ca.unb.mobiledev.saferide.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "driver_shift",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["driver_id"]
        ),
        ForeignKey(
            entity = Stations::class,
            parentColumns = ["station_name"],
            childColumns = ["station"]
        ),
        ForeignKey(
            entity = Vehicle::class,
            parentColumns = ["licence_plate"],
            childColumns = ["vehicle"]
        )
    ]
)
class DriverShift {
    @PrimaryKey(autoGenerate = true)
    val id = 0
    var driver_id: Int? = null
    var station: Stations? = null
    var vehicle: Vehicle? = null
    var frontView: ByteArray? = null
    var backView: ByteArray? = null
    var leftView: ByteArray? = null
    var rightView: ByteArray? = null
}