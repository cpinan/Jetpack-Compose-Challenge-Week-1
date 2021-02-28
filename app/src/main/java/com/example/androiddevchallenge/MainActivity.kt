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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.mutableStateOf
import com.example.androiddevchallenge.components.PuppyScaffold
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.data.puppies
import com.example.androiddevchallenge.ui.dashboard.DashboardScreen
import com.example.androiddevchallenge.ui.detail.DetailScreen

const val DASHBOARD_SCREEN = 1
const val DETAIL_SCREEN = 2

private val currentScreen = mutableStateOf(DASHBOARD_SCREEN)
private var currentPuppy = mutableStateOf(puppies[0])

class MainActivity : AppCompatActivity() {
    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PuppyScaffold {
                DashboardScreen()
                AnimatedVisibility(visible = currentScreen.value == DETAIL_SCREEN) {
                    DetailScreen(currentPuppy.value)
                }
            }
        }
    }

    override fun onBackPressed() {
        if (currentScreen.value == DETAIL_SCREEN) {
            currentScreen.value = DASHBOARD_SCREEN
            return
        }
        super.onBackPressed()
    }
}

fun puppyClicked(puppy: Puppy) {
    if (currentScreen.value == DASHBOARD_SCREEN) {
        currentPuppy.value = puppy
        currentScreen.value = DETAIL_SCREEN
    }
}

/*


// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Text(text = "Ready... Set... GO!")
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    PuppyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    PuppyTheme(darkTheme = true) {
        MyApp()
    }
}

 */
