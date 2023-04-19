package com.sadvakassov.videowizardtutorial

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.exoplayer2.ExoPlayer

@Composable
fun Page(
  pageData: PageData,
  exoPlayer: ExoPlayer,
  isPlaying: Boolean,
  currentIdx: Int,
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
  ) {
    VideoPlayer(
      exoPlayer,
      pageData.videoPath,
      isPlaying
    )

    Text(
      modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = 24.dp)
        .align(Alignment.BottomCenter),
      text = "Page $currentIdx",
      color = MaterialTheme.colorScheme.primary,
      textAlign = TextAlign.Center
    )
  }
}
