package com.vigor.hotelapp.di

import android.content.Context
import com.vigor.hotelapp.data.HotelRepository
import com.vigor.hotelapp.data.local.AppDatabase
import com.vigor.hotelapp.data.local.HotelDao
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
    fun provideHotelDao(database: AppDatabase): HotelDao {
        return database.hotelDao()
    }

    @Provides
    @Singleton
    fun provideHotelRepository(hotelDao: HotelDao): HotelRepository {
        return HotelRepository(hotelDao)
    }
}