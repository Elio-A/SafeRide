package ca.unb.mobiledev.saferide.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ca.unb.mobiledev.saferide.entity.User
import ca.unb.mobiledev.saferide.repository.UserRepository
import java.util.concurrent.Future

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository = UserRepository(application)

    val allUsers: LiveData<List<User>> = userRepository.allUsers
    val allDrivers: LiveData<List<User>> = userRepository.allDrivers

    fun insert(studentId: Int, firstName: String, lastName: String, email: String, password: String, driver: Boolean) {
        userRepository.insertUser(studentId, firstName, lastName, email, password, driver)
    }

    // Directly return LiveData<User> from the repository
    fun getUserById(id: Int, password: String): Future<List<User>> {
        return userRepository.searchUserById(id, password)
    }
}
