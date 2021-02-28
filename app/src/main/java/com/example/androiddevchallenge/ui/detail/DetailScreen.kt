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

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
            .fillMaxHeight()
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {

            Card(
                modifier = Modifier
                    .clickable {
                        clickAdoption.value = true
                    },
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.Yellow

            ) {
                Text(
                    text = stringResource(R.string.Adoption),
                    modifier = Modifier.padding(16.dp),
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
