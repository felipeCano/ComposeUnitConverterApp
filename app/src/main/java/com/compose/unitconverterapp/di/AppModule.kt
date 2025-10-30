package com.compose.unitconverterapp.di

import android.app.Application
import androidx.room.Room
import com.compose.unitconverterapp.data.ConverterDatabase
import com.compose.unitconverterapp.data.ConverterRepository
import com.compose.unitconverterapp.data.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConverterDatabase(app:Application): ConverterDatabase{
        return Room.databaseBuilder(app,ConverterDatabase::class.java,
            "converter_data_base"
        ).build()
    }

    @Provides
    @Singleton
    fun providerConverterRepository(db: ConverterDatabase): ConverterRepository{
        return ConverterRepositoryImpl(db.converterDao)
    }
}