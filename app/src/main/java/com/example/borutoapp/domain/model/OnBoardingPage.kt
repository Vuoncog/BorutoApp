package com.example.borutoapp.domain.model

import androidx.annotation.DrawableRes
import com.example.borutoapp.R

sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
){
    object First: OnBoardingPage(
        image = R.drawable.boruto,
        title = "Greetings",
        description = "Are you a Boruto fan? Because if you are then we have a great news for you!"
    )
    object Second: OnBoardingPage(
        image = R.drawable.karin,
        title = "Explore",
        description = "Find your favourite ninja and learn some of the things that you didn't know about."
    )
    object Third: OnBoardingPage(
        image = R.drawable.himawari,
        title = "Power",
        description = "Check out your ninja's power and see how much are they strong comparing to others."
    )
}
