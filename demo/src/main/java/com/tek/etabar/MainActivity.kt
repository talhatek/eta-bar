package com.tek.etabar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                color = Color(0xFF2C2929),
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {

                    ETABar(
                        elapsedPercentage = .6f,
                        inactiveBarColor = Color.Gray,
                        activeBarColor = Color.Yellow,
                        imageBitmap = AppCompatResources.getDrawable(LocalContext.current,R.drawable.ic_airplane)!!.toBitmap().asImageBitmap(),
                        modifier = Modifier.size(200.dp)
                    )
                }

            }
        }
    }
}