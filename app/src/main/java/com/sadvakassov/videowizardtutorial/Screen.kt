package com.sadvakassov.videowizardtutorial

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Screen(
  pages: List<PageData> = emptyList()
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val playersPool = rememberExoPlayersPool(pages)
    var selectedPage by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = pagerState) {
      snapshotFlow { pagerState.currentPage }.collect { page ->
        selectedPage = page
      }
    }

    HorizontalPager(
      modifier = Modifier
        .weight(1f)
        .fillMaxWidth(),
      pageCount = pages.size,
      state = pagerState,
      beyondBoundsPageCount = 1
    ) { index ->
      val page = pages[index]
      val exoPlayer = playersPool.createAndGet(index)

      Page(
        pageData = page,
        exoPlayer = exoPlayer,
        isPlaying = index == selectedPage,
        currentIdx = index
      )
    }

    Button(
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(vertical = 24.dp),
      onClick = {
        scope.launch {
          pagerState.animateScrollToPage(selectedPage + 1)
        }
      }) {
      Text(
        text = "Next",
        textAlign = TextAlign.Center
      )
    }
  }
}

@Composable
private fun rememberExoPlayersPool(
  pages: List<PageData>
): ExoPlayersPool {
  val context = LocalContext.current
  val playersPool = remember { ExoPlayersPool(context, pages) }

  DisposableEffect(Unit) {
    onDispose {
      playersPool.releaseAll()
    }
  }

  return playersPool
}
