package hr.ferit.markobudimir.konto.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import hr.ferit.markobudimir.konto.model.Company
import hr.ferit.markobudimir.konto.model.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserRepositoryImpl(
    firebaseAuth: FirebaseAuth,
    databaseReference: DatabaseReference,
) : UserRepository {
    private val ref = databaseReference.child("users")
        .child(firebaseAuth.currentUser!!.uid)

    override fun getUserData(): Flow<User?> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.child("Name").value.toString()
                Log.d("UserRepositoryImpl", "onDataChange: $name")
                val revenues = snapshot.child("revenues").value.toString()
                val expenses = snapshot.child("expenses").value.toString()
                val profit = snapshot.child("profit").value.toString()
                trySend(User(name, revenues, expenses, profit))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(User(error.message, "", "", ""))
            }
        }
        ref.addValueEventListener(listener)

        awaitClose {
            ref.removeEventListener(listener)
        }
    }

    override fun getCustomerObligations(path: String): Flow<List<Company>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("CompaniesUserRepositoryImpl", "snapshot: $snapshot")
                val list = mutableListOf<Company>()
                for (child in snapshot.children) {
                    Log.d("CompaniesUserRepositoryImpl", "child: $child")
                    val name = child.child("tvrtka").value.toString()
                    Log.d("CompaniesUserRepositoryImpl", "name_onDataChange: $name")
                    val amount = child.child("debt").value.toString()
                    val company = Company(name, amount)
                    list.add(company)
                }
                trySend(list)
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(listOf(Company(error.message, "")))
            }
        }
        ref.child(path).addValueEventListener(listener)

        awaitClose {
            ref.child(path).removeEventListener(listener)
        }
    }

    override fun getDebts(): Flow<List<Company>> {
        TODO("Not yet implemented")
    }
}
