package ca.unb.mobiledev.saferide.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ca.unb.mobiledev.saferide.entity.User

@Dao
interface User_DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Query("SELECT * from user where user_id = :user_id")
    infix fun getUserById(user_id: Int): LiveData<User>

    @Query("SELECT * from user")
    fun listAllUsers(): LiveData<List<User>>

    @Query("SELECT * from user where driver=1")
    fun getAllDrivers(): LiveData<List<User>>

}