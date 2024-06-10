//package com.ryaza.expensemanager.viewmodels
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.ryaza.expensemanager.repository.Repository
//import com.ryaza.expensemanager.utilities.SharedPrefs
//
//class ViewModelAuthenticationFactory(
//    private val userRepository: Repository,
//    private val sharedPrefs: SharedPrefs
//) :
//    ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return com.ryaza.expensemanager.viewmodels.ViewModelAuthentication(
//            userRepository,
//            sharedPrefs
//        ) as T
//    }
//}