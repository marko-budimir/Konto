package hr.ferit.markobudimir.konto.data.repository

import hr.ferit.markobudimir.konto.model.Bill

interface BillRepository {
    fun sendBill( bill: Bill)
}
