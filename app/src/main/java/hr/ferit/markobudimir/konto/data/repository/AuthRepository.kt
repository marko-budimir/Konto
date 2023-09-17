package hr.ferit.markobudimir.konto.data.repository

import com.google.firebase.auth.AuthResult
import hr.ferit.markobudimir.konto.util.Resource
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    fun login(email: String, password: String): Flow<Resource<AuthResult>>
    fun logout()
}
