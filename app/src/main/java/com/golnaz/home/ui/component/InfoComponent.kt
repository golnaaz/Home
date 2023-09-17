package com.golnaz.home.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun InfoComponent(title: String, value: String) {
    Row {
        Text(text = title, fontWeight = FontWeight(200))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = value, fontWeight = FontWeight(400))
    }
}