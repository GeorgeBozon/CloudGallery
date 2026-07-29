package ru.khubulty.authImpl.features.signup.presentation

import androidx.lifecycle.ViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.khubulty.authImpl.features.signup.domain.usecase.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase): ViewModel() {

}