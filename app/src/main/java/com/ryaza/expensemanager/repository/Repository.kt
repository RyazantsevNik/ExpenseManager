package com.ryaza.expensemanager.repository

import androidx.lifecycle.LiveData
import com.ryaza.expensemanager.model.TotalAmount
import com.ryaza.expensemanager.room.ExpenseDao
import com.ryaza.expensemanager.room.ExpenseEntity
import com.ryaza.expensemanager.room.UserDao
import com.ryaza.expensemanager.room.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val expenseDao: ExpenseDao,
    private val userDao: UserDao
) {

    fun getExpense(): LiveData<List<ExpenseEntity>> {
        return expenseDao.getExpense()
    }

    suspend fun insertExpense(expense: ExpenseEntity) {
        expenseDao.insertExpense(expense)
    }

    fun getTotalAmount(email: String, type: String): LiveData<List<TotalAmount>> {
        return expenseDao.getTotalAmount(email, type)
    }

    fun getAllExpenseType(email: String, expenseType: String): LiveData<List<ExpenseEntity>> {
        return expenseDao.getAllExpenseType(email, expenseType)
    }

    fun getByDate(date: String): LiveData<List<ExpenseEntity>> {
        return expenseDao.getByDate(date)
    }

    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    fun validateEmail(email: String): LiveData<UserEntity> {
        return userDao.validateEmail(email)
    }

    fun validatePassword(password: String): LiveData<UserEntity> {
        return userDao.validatePassword(password)
    }
}