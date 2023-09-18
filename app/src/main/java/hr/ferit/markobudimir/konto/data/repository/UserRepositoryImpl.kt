package hr.ferit.markobudimir.konto.data.repository

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
    private val firebaseAuth: FirebaseAuth,
    databaseReference: DatabaseReference,
) : UserRepository {
    private val ref = databaseReference.child("users")

    override fun getUserData(): Flow<User?> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.child("Name").value.toString()
                val revenues = snapshot.child("revenues").value.toString()
                val expenses = snapshot.child("expenses").value.toString()
                val profit = snapshot.child("profit").value.toString()
                val owner = snapshot.child("owner").value.toString()
                val address = snapshot.child("address").value.toString()
                val zipCode = snapshot.child("zipCode").value.toString()
                val pin = snapshot.child("pin").value.toString()
                trySend(User(name, revenues, expenses, profit, owner, address, zipCode, pin))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(User(error.message, "", "", ""))
            }
        }
        ref.child(firebaseAuth.currentUser!!.uid).addValueEventListener(listener)

        awaitClose {
            ref.child(firebaseAuth.currentUser!!.uid).removeEventListener(listener)
        }
    }

    override fun getCompanies(path: String): Flow<List<Company>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Company>()
                for (child in snapshot.children) {
                    val name = child.child("tvrtka").value.toString()
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
        ref.child(firebaseAuth.currentUser!!.uid).child(path).addValueEventListener(listener)

        awaitClose {
            ref.child(firebaseAuth.currentUser!!.uid).child(path).removeEventListener(listener)
        }
    }
}
