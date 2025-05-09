package com.vigor.hotelapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vigor.hotelapp.model.Booking

@Database(entities = [HotelEntity::class, Booking::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hotelDao(): HotelDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: android.content.Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "hotel_database"
                )
                    .allowMainThreadQueries() // For debugging only; remove in production
                    .fallbackToDestructiveMigration() // Handle schema changes
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}