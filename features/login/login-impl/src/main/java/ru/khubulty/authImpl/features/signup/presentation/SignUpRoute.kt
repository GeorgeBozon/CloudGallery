package ru.khubulty.authImpl.features.signup.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun SignUpRoute(onBack: () -> Unit) {
    val viewModel = hiltViewModel<SignUpViewModel>()

    SignUpContent(viewModel)

}

@Composable
fun SignUpContent(viewModel: SignUpViewModel){

}