package com.compose.unitconverterapp.compose.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.compose.unitconverterapp.data.ConversionResult

@Composable
fun HistoryList(
    list: State<List<ConversionResult>>,
    onCloseTask: (ConversionResult) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(
            items = list.value,
            key = { item -> item.id }
        ) { items ->
            HistoryItem(
                messagePart1 = items.messagePart1,
                messagePart2 = items.messagePart2,
                onClose = {onCloseTask(items)})
        }
    }
}