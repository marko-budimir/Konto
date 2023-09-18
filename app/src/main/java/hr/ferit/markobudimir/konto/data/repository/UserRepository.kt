package hr.ferit.markobudimir.konto.data.repository

import hr.ferit.markobudimir.konto.model.Company
import hr.ferit.markobudimir.konto.model.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    fun getUserData(): Flow<User?>
    fun getCompanies(path: String): Flow<List<Company>>
}
