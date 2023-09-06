package com.example.borutoapp.display.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.ui.theme.ERROR_ICON_SIZE
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.backgroundTheme
import com.example.borutoapp.ui.theme.fontTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StateScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>
) {
    val icon = remember { mutableStateOf(R.drawable.disconnection) }
    val message = remember {
        mutableStateOf(
            parseErrorMessage(
                error = error.toString(),
                icon = icon
            )
        )
    }

    if (error == null) {
        icon.value = R.drawable.magnifying_glass
        message.value = "Find your favourite heroes"
    }

    var isRefreshing by remember { mutableStateOf(false) }
    val state = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            heroes.refresh()
            isRefreshing = false
        }
    )

    Box(
        modifier = Modifier.pullRefresh(
            state = state,
            enabled = error != null
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = MaterialTheme.colors.backgroundTheme),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(ERROR_ICON_SIZE)
                    .padding(bottom = MEDIUM_PADDING),
                imageVector = ImageVector.vectorResource(id = icon.value),
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

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = state,
            modifier = Modifier.align(Alignment.TopCenter)
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
    StateScreen(
        error = LoadState.Error(SocketTimeoutException()),
        heroes = flowOf(PagingData.from(emptyList<Hero>())).collectAsLazyPagingItems()
    )
}