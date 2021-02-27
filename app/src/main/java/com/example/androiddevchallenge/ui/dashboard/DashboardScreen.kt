package com.example.androiddevchallenge.ui.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.data.puppies
import com.example.androiddevchallenge.puppyClicked
import com.example.androiddevchallenge.ui.theme.shapes
import com.example.androiddevchallenge.ui.theme.typography

/**
 * @author Carlos Piñan
 */
@ExperimentalFoundationApi
@Composable
fun DashboardScreen() {
    Surface(color = MaterialTheme.colors.background) {
        PuppyList()
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PuppyList() {
    /**
    items(puppies) { puppy ->
    PuppyElement(puppy)
    }
     */
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
    ) {
        itemsIndexed(puppies) { i, puppy ->
            PuppyElement(puppy, i)
        }
    }
}

@Preview
@Composable
fun PuppyElement(
    puppy: Puppy = puppies[0],
    idx: Int = 0
) {
    Column {
        if (idx % 2 != 0) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            )
        }
        Card(
            modifier = Modifier
                .padding(16.dp)
                .clip(shapes.small)
                .height(240.dp)
                .clickable {
                    puppyClicked(puppy = puppy)
                },
            elevation = 6.dp
        ) {
            ConstraintLayout {
                val (image, info) = createRefs()

                Image(
                    painter = painterResource(id = puppy.image),
                    contentDescription = puppy.description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                )

                Column(
                    modifier = Modifier
                        .constrainAs(info) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(16.dp, 24.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = puppy.name,
                        style = typography.h4,
                        textAlign = TextAlign.Left
                    )

                    Row {
                        Text(
                            text = puppy.time,
                            style = typography.h5,
                            textAlign = TextAlign.Left
                        )

                        if (puppy.isMale) {
                            Text(
                                text = "Boy",
                                style = typography.h5,
                                textAlign = TextAlign.Left
                            )
                        } else {
                            Text(
                                text = "Girl",
                                style = typography.h5,
                                textAlign = TextAlign.Left
                            )
                        }
                    }
                }

            }

        }
    }
}