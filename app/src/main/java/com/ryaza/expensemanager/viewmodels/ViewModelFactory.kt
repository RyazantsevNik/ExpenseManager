//package com.ryaza.expensemanager.viewmodels
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.ryaza.expensemanager.repository.Repository
//
//class ViewModelFactory(private val experseRepository: Repository) : ViewModelProvider.Factory{
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return com.ryaza.expensemanager.viewmodels.ViewModel(experseRepository) as T
//    }
//}