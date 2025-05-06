package com.vigor.hotelapp.di

import android.content.Context
import com.vigor.hotelapp.data.BookingRepository
import com.vigor.hotelapp.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideBookingRepository(database: AppDatabase): BookingRepository {
        return BookingRepository(database.bookingDao())
    }
}