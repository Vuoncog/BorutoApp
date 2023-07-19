package com.example.borutoapp.display.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
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
import com.google.accompanist.pager.HorizontalPagerIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen() {
    val boards = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val statusPager = rememberPagerState(pageCount = { boards.size })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colors.backgroundTheme
            )
            .padding(bottom = MEDIUM_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = statusPager,
            modifier = Modifier.weight(12f)
        ) { position ->
            BoardingPagerScreen(onBoardingPage = boards[position])
        }

        HorizontalPagerIndicator(
            modifier = Modifier.weight(0.5f),
            pagerState = statusPager,
            pageCount = boards.size,
            activeColor = MaterialTheme.colors.activeIndicatorTheme,
            inactiveColor = LightGray,
            indicatorWidth = PAGER_INDICATOR_WIDTH,
            spacing = PAGER_INDICATOR_SPACE
        )

        FinishButton(
            pagerState = statusPager,
            modifier = Modifier.weight(1f)
        ) {

        }
    }
}

@Composable
fun BoardingPagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = MEDIUM_PADDING)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.7f)
                .padding(bottom = LARGE_PADDING),
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FinishButton(
    pagerState: PagerState,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        AnimatedVisibility(
            visible = pagerState.currentPage == 2,
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = EXTRA_PADDING)
            ) {
                Text(text = stringResource(R.string.finish).uppercase())
            }
        }
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