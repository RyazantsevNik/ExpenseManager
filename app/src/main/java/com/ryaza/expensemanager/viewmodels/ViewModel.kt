package com.ryaza.expensemanager.viewmodels

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryaza.expensemanager.activity.categories
import com.ryaza.expensemanager.model.AddData
import com.ryaza.expensemanager.model.TotalAmount
import com.ryaza.expensemanager.repository.Repository
import com.ryaza.expensemanager.room.ExpenseEntity
import com.ryaza.expensemanager.utilities.Validate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


// create factory behind the scene
@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    fun getExpense(): LiveData<List<ExpenseEntity>> {
        return repository.getExpense()
    }

    fun insertExpense(expense: ExpenseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertExpense(expense)
        }
    }

    fun getTotalAmount(email: String, type: String): LiveData<List<TotalAmount>> {
        return repository.getTotalAmount(email, type)
    }

    fun getAllExpenseType(email: String, expenseType: String): LiveData<List<ExpenseEntity>> {
        return repository.getAllExpenseType(email, expenseType)
    }

    private var selectedCategory = ""
    fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
         selectedCategory = categories[position]
    }

    var _title = MutableLiveData<String>()
    var amount = MutableLiveData<String>()
    var description = MutableLiveData<String>()
    var isIncome = MutableLiveData<Boolean>().apply { value = true }  //Default value

//    var _hidebottomsheet = MutableLiveData<Boolean>().apply { value = false }
//    var hidebottomsheet: LiveData<Boolean> = _hidebottomsheet


    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    private val _errorMessageTitle = MutableLiveData<String>()
    val errorMessageTitle: LiveData<String> = _errorMessageTitle

    private val _errorMessageAmount = MutableLiveData<String>()
    val errorMessageAmount: LiveData<String> = _errorMessageAmount

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> = _selectedDate

    private val _selectedTime = MutableLiveData<String>()
    private val selectedTime: LiveData<String> = _selectedTime

    fun updateDate(newDate: String) {
        _selectedDate.value = newDate
    }

    fun updateTime(newTime: String) {
        _selectedTime.value = newTime
    }


    fun saveClick() {
        val obj = AddData(
            0,
            "ryazantsev@gmail.com",
            selectedCategory,
            _title.value?.trim() ?: "",
            _selectedTime.value ?: "",
            _selectedDate.value ?: "",
            description.value ?: "",
            amount.value?.trim() ?: "",
            isIncome.value ?: true
        )
        val titleError = Validate.validateTitle(obj.title)
        _errorMessageTitle.postValue(titleError)
        val amountError = Validate.validateAmount(obj.amount)
        _errorMessageAmount.postValue(amountError)
        if (titleError.isNotEmpty() || amountError.isNotEmpty()) {
            // Display error messages or handle the validation errors as needed
            _errorMessageTitle.postValue(titleError)
            _errorMessageAmount.postValue(amountError)
        } else {
            val type = if (obj.isIncome) "Expense" else "Income"
            insertExpense(
                ExpenseEntity(
                    obj.id,
                    obj.userEmail,
                    obj.category,
                    obj.title,
                    obj.time,
                    obj.date,
                    obj.description,
                    obj.amount,
                    type
                )
            )
            _title.value = ""
            amount.value = ""
            description.value = ""
//            _toastMessage.postValue("Saved")
//            _hidebottomsheet.value = true
        }

    }

}
