package com.immortalidiot.notifications.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MainScreen(
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val buttonModifier = Modifier.fillMaxWidth(0.7f)

        Button(
            modifier = buttonModifier,
            onClick = onFirstButtonClick
        ) {
            Text(
                text = "Post notification",
                textAlign = TextAlign.Center
            )
        }

        Button(
            modifier = buttonModifier,
            onClick = onSecondButtonClick
        ) {
            Text(
                text = "Post notification with go home",
                textAlign = TextAlign.Center
            )
        }
    }
}
