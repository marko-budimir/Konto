package hr.ferit.markobudimir.konto.ui.settings.mapper

import hr.ferit.markobudimir.konto.model.User
import hr.ferit.markobudimir.konto.ui.settings.SettingsUserDataViewState

interface SettingsMapper {
    fun toSettingsUserDataViewState(user: User): SettingsUserDataViewState
}
