package ca.unb.mobiledev.saferide.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.unb.mobiledev.saferide.repository.UserRepository

class UserViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    private val userRepository = UserRepository(application)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
