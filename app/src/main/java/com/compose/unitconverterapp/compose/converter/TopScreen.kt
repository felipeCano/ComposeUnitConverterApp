package com.compose.unitconverterapp.compose.converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import java.text.DecimalFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.compose.unitconverterapp.data.Conversion
import java.math.RoundingMode

@Composable
fun TopScreen(
    list: List<Conversion>,
    selectedConversion: MutableState<Conversion?>,
    inputText: MutableState<String>,
    typeValue: MutableState<String>,
    isLandscape: Boolean,
    save: (String, String) -> Unit
) {

    var toSave by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        ConversionMenu(list, isLandscape) {
            selectedConversion.value = it
            typeValue.value = "0.0"
        }

        selectedConversion.value?.let {
            InputBlock(conversion = it, inputText = inputText, isLandscape = isLandscape) { input ->
                typeValue.value = input
                toSave = true
            }
        }

        if (typeValue.value != "0.0") {
            val input = typeValue.value.toDouble()
            val multiply = selectedConversion.value!!.multiplyBy
            val result = input * multiply

            //rounding off the result to 4 decimal places
            val df = DecimalFormat("#.####")
            df.roundingMode = RoundingMode.DOWN
            val roundedResult = df.format(result)

            val message1 =
                "${typeValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
            val message2 = "${roundedResult} ${selectedConversion.value!!.convertTo}"
            if (toSave) {
                save(message1, message2)
                toSave = false
            }

            ResultBlock(message1 = message1, message2 = message2)
        }
    }
}