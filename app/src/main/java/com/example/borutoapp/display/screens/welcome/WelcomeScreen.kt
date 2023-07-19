package com.example.borutoapp.display.screens.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.OnBoardingPage
import com.example.borutoapp.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen() {
    val boards = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val statusPager = rememberPagerState(pageCount = { boards.size })
    HorizontalPager(
        state = statusPager,
        modifier = Modifier.fillMaxSize()
    ) { position ->
        BoardingPagerScreen(onBoardingPage = boards[position])
    }
}

@Composable
fun BoardingPagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                color = MaterialTheme.colors.backgroundTheme
            )
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.8f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(R.string.on_boarding_image)
        )
        Text(
            text = onBoardingPage.title,
            color = MaterialTheme.colors.fontTheme,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Text(
            text = onBoardingPage.description,
            color = LightGray,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = EXTRA_PADDING)
                .padding(top = MEDIUM_PADDING)
        )
    }
}

@Preview
@Composable
fun FirstBoardingPage() {
    BoardingPagerScreen(onBoardingPage = OnBoardingPage.First)
}

@Preview
@Composable
fun SecondBoardingPage() {
    BoardingPagerScreen(onBoardingPage = OnBoardingPage.Second)
}

@Preview
@Composable
fun ThirdBoardingPage() {
    BoardingPagerScreen(onBoardingPage = OnBoardingPage.Third)
}