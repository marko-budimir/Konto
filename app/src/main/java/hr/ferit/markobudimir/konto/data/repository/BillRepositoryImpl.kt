package hr.ferit.markobudimir.konto.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import hr.ferit.markobudimir.konto.model.Bill

class BillRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    databaseReference: DatabaseReference
) : BillRepository {
    private val ref = databaseReference.child("users")
    override fun sendBill(bill: Bill) {
        try {
            val key = ref.child(firebaseAuth.currentUser!!.uid)
                .child("bills")
                .push()
                .key
            Log.d("BillRepositoryImpl", "sendBill: $key")
            ref.child(firebaseAuth.currentUser!!.uid)
                .child("bills")
                .child(key!!)
                .setValue(bill)
        } catch (e: Exception) {
            Log.d("BillRepositoryImpl", "sendBill: ${e.message}")
        }

    }
}
