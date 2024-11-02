package ca.unb.mobiledev.saferide.repository

import android.app.Application
import androidx.lifecycle.LiveData
import ca.unb.mobiledev.saferide.dao.User_DAO
import ca.unb.mobiledev.saferide.db.database
import ca.unb.mobiledev.saferide.db.database.Companion.getDatabase
import ca.unb.mobiledev.saferide.entity.User

class UserRepository (application: Application) {
    private val userDao: User_DAO = getDatabase(application).userDao()

    val allUsers: LiveData<List<User>> = userDao.listAllUsers()

    val allDrivers: LiveData<List<User>> = userDao.getAllDrivers()

    fun insertUser(firstName: String?, lastName: String?, email: String?, driver: Boolean){
        val newUser = User()
        newUser.firstName = firstName
        newUser.lastName = lastName
        newUser.email = email
        newUser.driver = driver
        insert(newUser)

    }

    private fun insert(user: User){
        database.databaseWriterExecutor.execute{ userDao.insert(user) }
    }
}