package com.ryaza.expensemanager.model

data class AddData(
    val id: Long,
    val userEmail: String,
    val category: String,
    val title: String,
    val time: String,
    val date: String,
    val description: String,
    val amount: String,
    val isIncome: Boolean
)