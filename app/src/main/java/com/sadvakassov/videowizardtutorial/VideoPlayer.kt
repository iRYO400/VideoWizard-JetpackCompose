package com.sadvakassov.videowizardtutorial

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun VideoPlayer(
  exoPlayer: ExoPlayer,
  videoPath: String,
  isPlaying: Boolean
) {
  val context = LocalContext.current
  val playerView = remember {
    exoPlayer.apply {
      setMediaItem(MediaItem.fromUri(videoPath), true)
      prepare()
    }
    StyledPlayerView(context).apply {
      resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH
      useController = false
      setShutterBackgroundColor(Color.WHITE)
      player = exoPlayer
    }
  }
  AndroidView(factory = { playerView })

  exoPlayer.playWhenReady = isPlaying
}
