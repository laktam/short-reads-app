package org.mql.laktam.shortreads.ui.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import org.mql.laktam.shortreads.models.Post

@Composable
fun PostSlider(posts: List<Post>) {
    val pagerState = rememberPagerState (pageCount = { posts.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        PostItem(posts[page])
    }
}

@Composable
fun PostItem(post: Post) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(9f / 16f)//4f / 3f
    ) {
        if (post.backgroundUrl.isNotEmpty()) {
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(post.backgroundUrl)
//                    .size(Size.ORIGINAL)
//                    .build(),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.fillMaxSize()
//            )
            Image(
                painter = rememberAsyncImagePainter("http://10.0.2.2/short-reads-backend/${post.backgroundUrl}"),
                contentDescription = "Background image",
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(9f / 16f),
                contentScale = ContentScale.Crop,
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
        }
        println("post bg :::: ${post.backgroundUrl}")
        Text(
            text = post.content,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
                .background(
                    Color.Black.copy(alpha = 0.4f),
//                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )
    }
}