package com.ygolubev.pexels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ygolubev.pexels.ui.PexelsListScreen
import com.ygolubev.pexels.ui.theme.PexelsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PexelsTheme {
                PexelsListScreen()
            }
        }
    }
}
