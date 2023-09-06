package com.example.borutoapp.display.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.ICON_NORMAL_SIZE
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.ui.theme.fontTheme

@Composable
fun FullInfoBox(
    modifier: Modifier,
    power: Int,
    dateBorn: String,
    monthBorn: String,
    favorite: Double,
    iconColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.fontTheme
) {
    val listIcon = mapOf(
        R.drawable.power to Pair("Power", power),
        R.drawable.birthday to Pair("Birthday", "$dateBorn $monthBorn"),
        R.drawable.favorite to Pair("Favorite", favorite)
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.then(modifier)
    ) {
        listIcon.forEach {
            InfoBox(
                icon = ImageVector.vectorResource(id = it.key),
                iconColor = iconColor,
                contentText = it.value.second.toString(),
                descriptionText = it.value.first,
                textColor = contentColor
            )
        }
    }
}

@Composable
fun InfoBox(
    icon: ImageVector,
    iconColor: Color,
    contentText: String,
    descriptionText: String,
    textColor: Color,
) {
    Row() {
        Icon(
            modifier = Modifier.size(ICON_NORMAL_SIZE).
            padding(end = SMALL_PADDING),
            imageVector = icon,
            contentDescription = stringResource(R.string.icon_of_info_box),
            tint = iconColor
        )

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = contentText,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Medium,
                color = textColor
            )

            Text(
                text = descriptionText,
                style = MaterialTheme.typography.overline,
                color = textColor,
                letterSpacing = MaterialTheme.typography.caption.letterSpacing
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoBoxPreview() {
    InfoBox(
        icon = ImageVector.vectorResource(id = R.drawable.power),
        iconColor = MaterialTheme.colors.primary,
        contentText = "100",
        descriptionText = "Power",
        textColor = MaterialTheme.colors.fontTheme
    )
}