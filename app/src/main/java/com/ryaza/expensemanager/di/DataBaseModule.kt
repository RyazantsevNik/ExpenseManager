package com.ryaza.expensemanager.di

import android.content.Context
import androidx.room.Room
import com.ryaza.expensemanager.room.DatabaseHelper
import com.ryaza.expensemanager.room.ExpenseDao
import com.ryaza.expensemanager.room.UserDao
import com.ryaza.expensemanager.utilities.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun providesUserDao(database: DatabaseHelper): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun providesExpenseDao(database: DatabaseHelper): ExpenseDao {
        return database.expenseDao()
    }

    @Provides
    @Singleton
    fun providesRoomDb(@ApplicationContext appContext: Context): DatabaseHelper {
        return Room.databaseBuilder(
            appContext,
            DatabaseHelper::class.java,
            "Expense"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext appContext: Context): SharedPrefs {
        return SharedPrefs(appContext)
    }
}