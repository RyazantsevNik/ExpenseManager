package com.ryaza.expensemanager.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var username: String,
    val phone: String,
    val email: String,
    val password: String,
)
