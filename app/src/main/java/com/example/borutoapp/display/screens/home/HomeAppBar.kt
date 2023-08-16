package com.example.borutoapp.display.screens.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.appBarTheme
import com.example.borutoapp.ui.theme.colorOnPrimary
import com.example.borutoapp.ui.theme.fontTheme

@Composable
fun HomeAppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.explore),
                color = MaterialTheme.colors.colorOnPrimary,
                style = MaterialTheme.typography.h6
            )
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    tint = MaterialTheme.colors.colorOnPrimary,
                    contentDescription = stringResource(R.string.search_icon)
                )
            }
        },
        backgroundColor = MaterialTheme.colors.appBarTheme
    )
}

@Preview
@Composable
fun HomeAppBarPreview() {
    HomeAppBar()
}