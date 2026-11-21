package com.example.raportmandiri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.raportmandiri.navigation.AppNavGraph
import com.example.raportmandiri.ui.theme.RaportMandiriTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RaportMandiriTheme {
                AppNavGraph()
            }
        }
    }
}