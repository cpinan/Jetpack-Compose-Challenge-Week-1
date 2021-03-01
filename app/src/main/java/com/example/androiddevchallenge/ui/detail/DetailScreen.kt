/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.data.puppies
import com.example.androiddevchallenge.ui.FunctionalityNotAvailablePopup

/**
 * @author Carlos Piñan
 */
@Preview
@Composable
fun DetailScreen(puppy: Puppy = puppies[0]) {
    val clickAdoption = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White),
    ) {

        ConstraintLayout(
            modifier = Modifier
                .height(400.dp)
                .background(Color.White)
        ) {
            val (image, info) = createRefs()

            Image(
                painter = painterResource(id = puppy.image),
                contentDescription = puppy.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop
            )

            Card(
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .padding(32.dp, 0.dp)
                    .constrainAs(info) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = puppy.name,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Text(
                        text = puppy.info,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )

        Text(
            text = puppy.description,
            maxLines = 10,
            textAlign = TextAlign.Justify,
            style = TextStyle(
                color = Color.Black,
                fontSize = 15.sp
            ),
            modifier = Modifier
                .padding(32.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {

            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .height(32.dp)
                    .width(32.dp)
                    .clickable { clickAdoption.value = true }
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.ic_heart),
                    contentDescription = null
                )
            }

            Spacer(
                modifier = Modifier
                    .width(16.dp)
            )

            Card(
                modifier = Modifier
                    .clickable { clickAdoption.value = true },
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color(0xfffbce56)
            ) {

                Text(
                    modifier = Modifier.padding(16.dp, 8.dp),
                    text = stringResource(R.string.Adoption),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )
    }

    if (clickAdoption.value) {
        FunctionalityNotAvailablePopup(
            onDismiss = {
                clickAdoption.value = false
            }
        )
    }
}
