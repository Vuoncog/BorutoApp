package com.example.borutoapp.display.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.borutoapp.R
import com.example.borutoapp.display.components.RatingWidget
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.navigation.Screen
import com.example.borutoapp.ui.theme.*
import com.example.borutoapp.utilities.Constants.BASE_URL

@Composable
fun HeroItem(
    hero: Hero,
    navController: NavHostController
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data("$BASE_URL${hero.image}")
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .build(),
    )

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(HERO_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Detail.getArgument(heroId = hero.id))
            }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(ROUND_CORNER_LARGE))
                .background(MaterialTheme.colors.backgroundTheme)
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painter,
                contentDescription = stringResource(R.string.image_hero),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )
        }

        Surface(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        bottomStart = ROUND_CORNER_LARGE,
                        bottomEnd = ROUND_CORNER_LARGE
                    )
                ),
            color = Color.Black.copy(ContentAlpha.medium)
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        vertical = SMALL_PADDING,
                        horizontal = MEDIUM_PADDING
                    )
            ) {
                Text(
                    text = hero.name,
                    style = MaterialTheme.typography.h5,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.colorOnPrimary
                )

                Text(
                    modifier = Modifier.padding(bottom = SMALL_PADDING),
                    text = hero.about,
                    style = MaterialTheme.typography.subtitle1,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.colorOnPrimary
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)
                ) {
                    RatingWidget(rating = hero.rating)
                    Text(
                        text = "(${hero.rating})",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Light,
                        color = LightGray
                    )
                }
            }
        }

    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HeroItemPreview() {
    HeroItem(
        hero = Hero(
            id = 1,
            name = "Sasuke",
            image = "images/sasuke_uchiha.jpg",
            about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki, Sasuke starts developing his skills, but eventually grows dissatisfied with his progress. He defects from Konoha so that he can acquire the strength needed to exact his revenge and master the Cursed Seal of Heaven. His years of seeking vengeance and his actions that followed become increasingly demanding, irrational and isolates him from others, leading him to be branded as an international criminal. After learning the truth of his brother's sacrifice, later proving instrumental in ending the Fourth Shinobi World War, and being happily redeemed by Naruto, Sasuke decides to return to Konoha and dedicate his life to help protect the village and its inhabitants, becoming referred to as the \"Supporting Kage\" (支う影, Sasaukage, literally meaning: Supporting Shadow).",
            rating = 4.4,
            power = 98,
            month = "July",
            day = "23rd",
            family = listOf(
                "Fugaku",
                "Mikoto",
                "Itachi",
                "Sarada",
                "Sakura"
            ),
            abilities = listOf(
                "Sharingan",
                "Rinnegan",
                "Eternal Mangekyō Sharingan",
            ),
            natureTypes = listOf(
                "Lightning",
                "Fire",
                "Wind",
                "Earth",
                "Water",
                "Yin"
            )
        ),
        navController = rememberNavController()
    )
}