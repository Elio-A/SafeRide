package ca.unb.mobiledev.saferide.repository

import android.app.Application
import androidx.lifecycle.LiveData
import ca.unb.mobiledev.saferide.dao.User_DAO
import ca.unb.mobiledev.saferide.db.AppDatabase
import ca.unb.mobiledev.saferide.db.AppDatabase.Companion.getDatabase
import ca.unb.mobiledev.saferide.entity.User
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class UserRepository(application: Application) {

    private val userDao: User_DAO = getDatabase(application).userDao()

    val allUsers: LiveData<List<User>> = userDao.listAllUsers()
    val allDrivers: LiveData<List<User>> = userDao.getAllDrivers()

    fun insertUser(studentId: Int, firstName: String, lastName: String, email: String, password: String, driver: Boolean) {
        val user = User(
            userId = studentId,
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            driver = driver
        )
        insert(user)
    }

    // Updated to return LiveData<User>
    fun searchUserById(id: Int, password: String): Future<List<User>> {
        val dataReadFuture: Future<List<User>> = AppDatabase.databaseWriterExecutor.submit(
            Callable {
                userDao.getUserById(id, password)
            }
        )
        return try{
            while(!dataReadFuture.isDone){
                TimeUnit.SECONDS.sleep(1)
            }
            dataReadFuture
        } catch (e: ExecutionException){
            e.printStackTrace()
            CompletableFuture.completedFuture(emptyList())
        } catch (e: InterruptedException){
            e.printStackTrace()
            CompletableFuture.completedFuture(emptyList())
        }
    }

    private fun insert(user: User){
        AppDatabase.databaseWriterExecutor.execute { userDao.insert(user) }
    }
}
