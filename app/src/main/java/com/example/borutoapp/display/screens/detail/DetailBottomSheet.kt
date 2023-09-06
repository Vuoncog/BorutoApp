package com.example.borutoapp.display.screens.detail

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.ui.theme.*
import com.example.borutoapp.utilities.Constants
import com.example.borutoapp.utilities.Constants.ABOUT_MAX_LINES

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailContent(
    hero: Hero,
    onCloseClicked: () -> Unit,
    textColor: Color = MaterialTheme.colors.fontTheme,
    iconColor: Color = MaterialTheme.colors.primary,
    backgroundColor: Color = MaterialTheme.colors.backgroundTheme
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed,
        )
    )

    val currentImageFraction = bottomSheetScaffoldState.currentSheetFraction
    val radiusAnim = animateDpAsState(
        targetValue =
        if (currentImageFraction == 1f) ROUND_CORNER_EXTRA
        else ROUND_CORNER_LARGE
    )

    val dividerFraction = floatMap(
        currentImageFraction, 0.6f, 1f, 0f, 0.5f
    )


    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim.value,
            topEnd = radiusAnim.value
        ),
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            DetailBottomSheet(
                dividerFraction = dividerFraction,
                hero = hero,
                textColor = textColor,
                iconColor = iconColor,
            )
        },
        sheetPeekHeight = MIN_INFO_BOX_HEIGHT,
        content = {
            DetailBackground(
                image = hero.image,
                imageFraction = currentImageFraction,
                onCloseClicked = onCloseClicked
            )
        },
        backgroundColor = backgroundColor,
        sheetBackgroundColor = backgroundColor
    )
}

@Composable
fun DetailBackground(
    image: String,
    imageFraction: Float = 1f,
    onCloseClicked: () -> Unit
) {
    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data("${Constants.BASE_URL}${image}")
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .build(),
    )

    Box(modifier = Modifier) {
        Image(
            painter = imagePainter,
            contentDescription = stringResource(R.string.image_background),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction)
        )
        IconButton(
            modifier = Modifier
                .padding(MEDIUM_PADDING)
                .align(Alignment.TopEnd),
            onClick = { onCloseClicked() }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = stringResource(id = R.string.close_icon),
                tint = Color.White
            )
        }
    }
}

@Composable
fun DetailBottomSheet(
    hero: Hero,
    dividerFraction: Float,
    textColor: Color = MaterialTheme.colors.fontTheme,
    iconColor: Color = MaterialTheme.colors.primary
) {
    Column(
        modifier = Modifier
            .padding(MEDIUM_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(
            color = textColor,
            thickness = DIVIDER_THICKNESS,
            modifier = Modifier.fillMaxWidth(dividerFraction)
        )
        TitleInfoBox(
            title = hero.name,
            textColor = textColor,
            iconColor = iconColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = SMALL_PADDING,
                    bottom = MEDIUM_PADDING
                )
        )
        FullInfoBox(
            modifier = Modifier.fillMaxWidth(),
            power = hero.power,
            dateBorn = hero.day,
            monthBorn = hero.month,
            favorite = hero.rating,
            iconColor = iconColor,
            contentColor = textColor
        )
        AboutInfo(
            about = hero.about,
            modifier = Modifier,
            textColor = textColor
        )
        FullOtherInfo(
            family = hero.family,
            abilities = hero.abilities,
            natureTypes = hero.natureTypes,
            textColor = textColor,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun AboutInfo(
    textColor: Color = MaterialTheme.colors.fontTheme,
    about: String,
    modifier: Modifier,
) {
    Column(
        modifier = Modifier.then(modifier)
    ) {
        Text(
            text = "About",
            style = MaterialTheme.typography.subtitle1,
            color = textColor,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = MEDIUM_PADDING / 2)
        )
        Text(
            text = about,
            style = MaterialTheme.typography.body2,
            maxLines = ABOUT_MAX_LINES,
            color = textColor
        )
    }
}

@Composable
fun TitleInfoBox(
    title: String,
    textColor: Color,
    iconColor: Color,
    modifier: Modifier
) {
    Row(
        modifier = Modifier
            .then(modifier)
    ) {
        Icon(
            modifier = Modifier
                .padding(end = SMALL_PADDING)
                .size(ICON_BIG_SIZE),
            imageVector = ImageVector.vectorResource(id = R.drawable.boruto_app_logo),
            contentDescription = stringResource(
                id = R.string.app_logo
            ),
            tint = iconColor
        )
        Text(
            text = title,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Medium,
            color = textColor
        )
    }
}

fun floatMap(x: Float, in_min: Float, in_max: Float, out_min: Float, out_max: Float): Float {
    return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
}

@OptIn(ExperimentalMaterialApi::class)
private val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val expandedFraction = floatMap(
            x = bottomSheetState.progress,
            in_min = 0f,
            in_max = 1f,
            out_min = 0.6f,
            out_max = 1f
        )

        val collapsedFraction = floatMap(
            x = bottomSheetState.progress,
            in_min = 0f,
            in_max = 1f,
            out_min = 1f,
            out_max = 0.6f
        )
        Log.d("Fraction", bottomSheetState.progress.toString())
        Log.d("Fraction State", bottomSheetState.currentValue.toString())
        Log.d("Fraction Collapsed", bottomSheetState.isCollapsed.toString())
        Log.d("Fraction Expanded", bottomSheetState.isExpanded.toString())

        return when {
            bottomSheetState.isCollapsed
                    && bottomSheetState.progress == 1f -> 1f
            bottomSheetState.isExpanded
                    && bottomSheetState.progress == 1f -> 0.6f
            bottomSheetState.isExpanded -> expandedFraction
            bottomSheetState.isCollapsed -> collapsedFraction
            else -> collapsedFraction
        }
    }

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    DetailContent(
        hero = Hero(
            id = 3,
            name = "Sakura",
            image = "/images/sakura.jpg",
            about = "Sakura Uchiha (うちはサクラ, Uchiha Sakura, née Haruno (春野)) is a kunoichi of Konohagakure. When assigned to Team 7, Sakura quickly finds herself ill-prepared for the duties of a shinobi. However, after training under the Sannin Tsunade, she overcomes this, and becomes recognised as one of the greatest medical-nin in the world.",
            rating = 4.5,
            power = 92,
            month = "Mar",
            day = "28th",
            family = listOf(
                "Kizashi",
                "Mebuki",
                "Sarada",
                "Sasuke"
            ),
            abilities = listOf(
                "Chakra Control",
                "Medical Ninjutsu",
                "Strength",
                "Intelligence"
            ),
            natureTypes = listOf(
                "Earth",
                "Water",
                "Fire"
            )
        ),
        onCloseClicked = {}
    )
}