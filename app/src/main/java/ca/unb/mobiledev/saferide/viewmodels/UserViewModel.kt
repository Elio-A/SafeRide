package ca.unb.mobiledev.saferide.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ca.unb.mobiledev.saferide.entity.User
import ca.unb.mobiledev.saferide.repository.UserRepository

class UserViewModel(application: Application, private val userRepository: UserRepository) : AndroidViewModel(application) {

    val allUsers: LiveData<List<User>> = userRepository.allUsers
    val allDrivers: LiveData<List<User>> = userRepository.allDrivers

    fun insert(user: User) {
        userRepository.insertUser(user)
    }

    // Directly return LiveData<User> from the repository
    fun getUserById(id: Int): LiveData<User> {
        return userRepository.searchUserById(id)
    }
}
