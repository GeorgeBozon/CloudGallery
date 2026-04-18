package ru.khubulty.cloudgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.khubulty.cloudgallery.data.UsersRepository
import ru.khubulty.cloudgallery.domain.UserDomain
import ru.khubulty.cloudgallery.ui.theme.CloudGalleryTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repo: UsersRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        application.component().inject(this)

        setContent {
            CloudGalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val users = remember { mutableStateListOf<UserDomain>() }

                    LaunchedEffect(Unit) {
                        val data = repo.getUsers()
                        users.addAll(data)
                    }
                    Box(Modifier
                        .padding(innerPadding)
                        .fillMaxSize()) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = users.firstOrNull()?.name ?: "Hello"
                        )
                    }
                }
            }
        }
    }
}