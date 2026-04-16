package ru.khubulty.cloudgallery

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.khubulty.cloudgallery.ui.theme.CloudGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CloudGalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnimateSize(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    var counter by remember { mutableIntStateOf(0) }

    Box(modifier = modifier.fillMaxSize()) {
        AnimatedContent(
            modifier = Modifier.align(Alignment.Center),
            targetState = counter, transitionSpec = {
                slideInHorizontally(animationSpec = tween(durationMillis = 500)) { -it } + fadeIn() togetherWith (
                        slideOutHorizontally(animationSpec = tween(durationMillis = 500)) { it + 300 }
                        ) using (SizeTransform(clip = false))
            }) {
            Text(
                text = "Hello $name! count is $it",
                modifier = Modifier
                    .background(Color.Blue)
                    .padding(16.dp)
            )
        }

        Button(
            onClick = { counter++ },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(16.dp)
        ) { Text("Click") }

    }
}

@Composable
fun AnimateSize(modifier: Modifier) {
    var isLarge by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        Text(
            modifier = Modifier
                .background(color = Color.Blue, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
                .animateContentSize(tween(1000))
                .align(Alignment.Center)
                .size(if (isLarge) 400.dp else 200.dp),
            textAlign = TextAlign.Center,
            text = "ClickSkock"
        )
val list = listOf(1,3,5).fold("dpdlp"){ i, c ->
    ""
}
        Button(
            onClick = { isLarge = !isLarge },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) { Text("Click") }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CloudGalleryTheme {
        Greeting("Android")
    }
}