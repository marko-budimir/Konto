package hr.ferit.markobudimir.konto.ui.home.mapper

import hr.ferit.markobudimir.konto.model.User
import hr.ferit.markobudimir.konto.ui.home.HomeUserDataViewState

interface HomeMapper {
        fun toHomeUserDataViewState(user: User): HomeUserDataViewState
}
