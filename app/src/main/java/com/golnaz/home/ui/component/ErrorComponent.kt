package com.golnaz.home.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorComponent(
    error: String,
    modifier: Modifier = Modifier
) {
    Text(modifier = modifier.padding(16.dp), text = error, color = Color.Red)
}

@Preview
@Composable
fun ErrorComponentPreview() {
    ErrorComponent("Error!")
}