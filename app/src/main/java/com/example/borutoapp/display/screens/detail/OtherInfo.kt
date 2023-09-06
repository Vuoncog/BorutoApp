package com.example.borutoapp.display.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.ui.theme.fontTheme

@Composable
fun FullOtherInfo(
    family: List<String>,
    abilities: List<String>,
    natureTypes: List<String>,
    textColor: Color,
    modifier: Modifier
) {
    val fullOtherInfo = mapOf(
        family to "Family",
        abilities to "Abilities",
        natureTypes to "Nature Types"
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.then(modifier)
    ) {
        fullOtherInfo.forEach {
            OtherInfo(
                title = it.value,
                items = it.key,
                textColor = textColor
            )
        }
    }
}

@Composable
fun OtherInfo(
    title: String,
    items: List<String>,
    textColor: Color
) {
    Column {
        Text(
            modifier = Modifier.padding(bottom = SMALL_PADDING),
            text = title,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Medium,
            color = textColor
        )

        items.forEachIndexed { index, item ->
            Text(
                text = "${index}. $item",
                style = MaterialTheme.typography.body2,
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OtherInfoPreview() {
    OtherInfo(
        title = "Family",
        items = listOf("Sasuke", "Naruto", "Sakura"),
        textColor = MaterialTheme.colors.fontTheme
    )
}