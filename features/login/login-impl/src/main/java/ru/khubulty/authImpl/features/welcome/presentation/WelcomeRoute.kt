package ru.khubulty.authImpl.presentation.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.khubulty.loginImpl.R
import ru.khubulty.ui.R.drawable.ic_cloud
import ru.khubulty.ui.colors.CreamYellow
import ru.khubulty.ui.colors.OceanColor


@Composable
fun WelcomeRoute(signUp: () -> Unit, logIn: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        WelcomeContent(modifier = Modifier.padding(paddingValues), signUp = signUp, logIn = logIn)
    }
}

@Composable
private fun WelcomeContent(modifier: Modifier, signUp: () -> Unit, logIn: () -> Unit) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(ic_cloud),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(100.dp)
        )
        Text(
            text = stringResource(R.string.welcome),
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium
        )

        ElevatedButton(
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 12.dp
            ),
            border = BorderStroke(2.dp, color = Color.Black),
            onClick = signUp,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 80.dp),
            colors = ButtonDefaults.buttonColors().copy(containerColor = OceanColor)
        ) {
            Text(
                stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black
            )
        }
        ElevatedButton(
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 12.dp
            ),
            border = BorderStroke(2.dp, color = Color.Black),
            onClick = logIn,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 16.dp),
            colors = ButtonDefaults.buttonColors().copy(containerColor = CreamYellow)
        ) {
            Text(
                stringResource(R.string.log_in),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black
            )
        }
    }
}