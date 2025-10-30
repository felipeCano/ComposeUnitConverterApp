package com.compose.unitconverterapp.data

import kotlinx.coroutines.flow.Flow

interface ConverterRepository {

    suspend fun insertResult(result: ConversionResult)
    suspend fun deleteResult(result: ConversionResult)
    suspend fun deleteAllResults()
    fun getSavedResult(): Flow<List<ConversionResult>>

}