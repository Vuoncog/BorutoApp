package com.example.borutoapp.display.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.*

@Composable
fun ShimmerEffect() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
        modifier = Modifier.padding(MEDIUM_PADDING)
    ) {
        items(
            count = 2
        ){
            AnimatedShimmerItem()
        }
    }
}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    ShimmerEffectItem(
        alpha = alphaAnim
    )
}

@Composable
fun ShimmerEffectItem(
    alpha: Float
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(HERO_ITEM_HEIGHT)
            .clip(RoundedCornerShape(ROUND_CORNER_LARGE))
            .background(MaterialTheme.colors.shimmerItemBackground)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = SMALL_PADDING,
                    horizontal = MEDIUM_PADDING
                )
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(bottom = MEDIUM_PADDING)
                    .alpha(alpha),
                color = MaterialTheme.colors.itemBackgroundOnPrimary,
                shape = RoundedCornerShape(ROUND_CORNER_MEDIUM)
            ) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.h6,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.colorOnPrimary
                )
            }

            repeat(3) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = SMALL_PADDING)
                        .alpha(alpha),
                    color = MaterialTheme.colors.itemBackgroundOnPrimary,
                    shape = RoundedCornerShape(ROUND_CORNER_SMALL)
                ) {
                    Text(
                        text = "",
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.colorOnPrimary
                    )
                }
            }

            val image = ImageBitmap.imageResource(id = R.drawable.star_outlined)
            val width = LocalDensity.current.run { image.width.toDp() }
            Row {
                repeat(5) {
                    Surface(
                        modifier = Modifier
                            .padding(end = MEDIUM_PADDING)
                            .size(width / 3 * 2)
                            .alpha(alpha),
                        color = MaterialTheme.colors.itemBackgroundOnPrimary,
                        shape = CircleShape
                    ) {}
                }

            }

        }
    }
}

@Preview(name = "item",uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ShimmerEffectItemPreview() {
    AnimatedShimmerItem()
}