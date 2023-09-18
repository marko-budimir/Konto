package hr.ferit.markobudimir.konto.ui.billdetails

import androidx.lifecycle.ViewModel
import hr.ferit.markobudimir.konto.data.repository.BillRepository
import hr.ferit.markobudimir.konto.model.Bill

class BillDetailsViewModel(private val billRepository: BillRepository) : ViewModel() {
    fun sendBill(bill: Bill) {
        billRepository.sendBill(bill)
    }
}
