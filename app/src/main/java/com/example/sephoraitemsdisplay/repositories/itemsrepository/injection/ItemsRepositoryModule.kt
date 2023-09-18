package com.example.sephoraitemsdisplay.repositories.itemsrepository.injection

import android.content.Context
import androidx.room.Room
import com.example.sephoraitemsdisplay.domains.items.repositories.ItemsRepository
import com.example.sephoraitemsdisplay.repositories.itemsrepository.ItemsRepositoryImpl
import com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.ItemsApiService
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.ItemsRoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
interface ItemsRepositoryModule {
    @Binds
    fun bindItemsRepository(impl: ItemsRepositoryImpl): ItemsRepository

    companion object {
        @Provides
        fun provideCharacterApiService(
            retrofit: Retrofit
        ): ItemsApiService = retrofit.create(ItemsApiService::class.java)

        @Provides
        fun provideCharacterDatabase(
            @ApplicationContext context: Context
        ) = Room.databaseBuilder(
            context,
            ItemsRoomDatabase::class.java,
            "category_db"
        ).build()

        @Provides
        fun provideItemsProductDao(db: ItemsRoomDatabase) = db.itemProductDao()
    }
}