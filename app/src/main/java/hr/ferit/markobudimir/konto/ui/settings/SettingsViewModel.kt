package hr.ferit.markobudimir.konto.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import hr.ferit.markobudimir.konto.data.repository.AuthRepository
import hr.ferit.markobudimir.konto.data.repository.UserRepository
import hr.ferit.markobudimir.konto.ui.settings.mapper.SettingsMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.TimeUnit

const val UNIQUE_WORK_NAME = "notification_work"

class SettingsViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val settingsMapper: SettingsMapper
) : ViewModel() {
    private val _settingsUserDataViewState = MutableStateFlow(
        SettingsUserDataViewState(
            companyName = "",
            address = "",
            zipCode = "",
            pin = "",
        )
    )
    val settingsUserDataViewState = _settingsUserDataViewState.asStateFlow()

    init {
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch {
            userRepository.getUserData().collect { user ->
                user?.let {
                    _settingsUserDataViewState.value =
                        settingsMapper.toSettingsUserDataViewState(it)
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

    fun scheduleNotification(day: Int, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        val currentTimeMillis = System.currentTimeMillis()
        val notificationTimeMillis = calendar.timeInMillis
        val initialDelay = if (currentTimeMillis < notificationTimeMillis) {
            notificationTimeMillis - currentTimeMillis
        } else {
            val nextMonth = Calendar.getInstance()
            nextMonth.add(Calendar.MONTH, 1)
            nextMonth.set(Calendar.DAY_OF_MONTH, day)
            nextMonth.set(Calendar.HOUR_OF_DAY, hour)
            nextMonth.set(Calendar.MINUTE, minute)
            nextMonth.set(Calendar.SECOND, 0)
            nextMonth.timeInMillis - currentTimeMillis
        }

        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(
            repeatInterval = 30,
            repeatIntervalTimeUnit = TimeUnit.DAYS
        ).setInitialDelay(initialDelay, TimeUnit.MILLISECONDS).build()

        val workManager = WorkManager.getInstance()
        workManager.enqueueUniquePeriodicWork(
            UNIQUE_WORK_NAME,
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            workRequest
        )
    }

    fun cancelNotification() {
        val workManager = WorkManager.getInstance()
        workManager.cancelUniqueWork(UNIQUE_WORK_NAME)
    }
}
