package com.example.borutoapp.display.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.ERROR_ICON_SIZE
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.backgroundTheme
import com.example.borutoapp.ui.theme.fontTheme
import java.net.SocketTimeoutException

@Composable
fun ErrorScreen(error: LoadState.Error) {
    val icon = remember { mutableStateOf(R.drawable.disconnection) }
    val message = remember {
        mutableStateOf(
            parseErrorMessage(
                error = error.toString(),
                icon = icon
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.backgroundTheme),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(ERROR_ICON_SIZE),
            painter = painterResource(id = icon.value),
            contentDescription = stringResource(R.string.error_icon),
            tint = MaterialTheme.colors.fontTheme.copy(ContentAlpha.disabled)
        )

        Text(
            text = message.value,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.fontTheme.copy(ContentAlpha.disabled)
        )
    }
}

fun parseErrorMessage(
    error: String,
    @DrawableRes icon: MutableState<Int>
): String {
    return when {
        error.contains("Connect") -> "No Internet Connection"
        error.contains("Socket") -> {
            icon.value = R.drawable.alert
            "Server Disruption"
        }
        else -> "Unknown Error"
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(error = LoadState.Error(SocketTimeoutException()))
}