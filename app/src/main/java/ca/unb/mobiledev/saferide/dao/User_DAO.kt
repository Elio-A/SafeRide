package ca.unb.mobiledev.saferide.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ca.unb.mobiledev.saferide.entity.User

@Dao
interface User_DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Query("SELECT * from user where user_id = :user_id")
    fun getUser(user_id: Int): List<User>

    @Query("SELECT * from user")
    fun listAllUsers(): LiveData<List<User>>


}