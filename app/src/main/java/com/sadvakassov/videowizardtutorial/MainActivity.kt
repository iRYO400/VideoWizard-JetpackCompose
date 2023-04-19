package com.sadvakassov.videowizardtutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sadvakassov.videowizardtutorial.ui.theme.VideoWizardTutorialTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      VideoWizardTutorialTheme {
        Screen(
          pages = listOf(
            PageData(
              "file:///android_asset/1.mp4",
              "Page Title"
            ),
            PageData(
              "file:///android_asset/2.mp4",
              "Page Title"
            ),
            PageData(
              "file:///android_asset/3.mp4",
              "Page Title"
            ),
            PageData(
              "file:///android_asset/4.mp4",
              "Page Title"
            )
          )
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  VideoWizardTutorialTheme {
    Screen()
  }
}