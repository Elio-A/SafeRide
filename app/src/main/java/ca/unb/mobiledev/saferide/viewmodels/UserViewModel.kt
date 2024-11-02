package ca.unb.mobiledev.saferide.viewmodels

import androidx.lifecycle.LiveData
import ca.unb.mobiledev.saferide.entity.User
import ca.unb.mobiledev.saferide.repository.UserRepository

class UserViewModel (private val repository: UserRepository) {
     val allusers: LiveData<List<User>> = repository.allUsers

    val allDrivers: LiveData<List<User>> = repository.allDrivers

    fun insert(firstName: String?, lastName: String?, email: String?, driver: Boolean){
        repository.insertUser(firstName, lastName, email, driver)
    }
}