package hr.ferit.markobudimir.konto.ui.home.mapper

import hr.ferit.markobudimir.konto.model.User
import hr.ferit.markobudimir.konto.ui.home.HomeUserDataViewState

class HomeMapperImpl : HomeMapper {
    override fun toHomeUserDataViewState(user: User): HomeUserDataViewState {
        return HomeUserDataViewState(user.name, user.revenues, user.expenses, user.profit)
    }
}
