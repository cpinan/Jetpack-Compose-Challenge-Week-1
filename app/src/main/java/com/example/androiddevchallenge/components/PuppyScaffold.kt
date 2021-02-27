package com.example.androiddevchallenge.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.example.androiddevchallenge.ui.theme.PuppyTheme
import com.example.androiddevchallenge.ui.theme.typography

/**
 * @author Carlos Piñan
 */
@Composable
fun PuppyScaffold(
    content: @Composable (PaddingValues) -> Unit
) {
    PuppyTheme {
        Scaffold(
            topBar = {
                TopAppBar {
                    Text(
                        text = "Awesome Puppy App",
                        textAlign = TextAlign.Center,
                        style = typography.h3
                    )
                }
            },
            content = content
        )
    }
}