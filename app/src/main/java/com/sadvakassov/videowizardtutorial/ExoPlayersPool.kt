package com.sadvakassov.videowizardtutorial

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player

class ExoPlayersPool(
  private val context: Context,
  pages: List<PageData>,
) {

  private val players = hashMapOf<Int, ExoPlayer>()

  init {
    prepareExoPlayers(pages)
  }

  private fun prepareExoPlayers(pages: List<PageData>) {
    pages.onEachIndexed { index, _ ->
      createAndGet(index)
    }
  }

  fun createAndGet(index: Int): ExoPlayer {
    val id = index % PLAYERS_COUNT
    if (players.contains(id)) {
      return players[id]!!
    }

    val exoPlayer = ExoPlayer.Builder(context)
      .build()
      .apply {
        repeatMode = Player.REPEAT_MODE_ALL
        volume = 0f
      }
    players[id] = exoPlayer
    return exoPlayer
  }

  fun releaseAll() {
    players.forEach {
      it.value.stop()
      it.value.release()
    }
    players.clear()
  }

  companion object {
    private const val PLAYERS_COUNT = 3
  }
}