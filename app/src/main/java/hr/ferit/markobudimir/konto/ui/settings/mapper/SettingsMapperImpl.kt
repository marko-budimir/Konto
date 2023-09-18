package hr.ferit.markobudimir.konto.ui.settings.mapper

import hr.ferit.markobudimir.konto.model.User
import hr.ferit.markobudimir.konto.ui.settings.SettingsUserDataViewState

class SettingsMapperImpl : SettingsMapper {
    override fun toSettingsUserDataViewState(user: User): SettingsUserDataViewState =
        SettingsUserDataViewState(user.name, user.address, user.zipCode, user.pin, user.owner)
}
