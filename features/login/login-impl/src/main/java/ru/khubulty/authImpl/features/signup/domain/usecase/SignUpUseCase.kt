package ru.khubulty.authImpl.features.signup.domain.usecase

import ru.khubulty.authImpl.features.signup.domain.repo.SignUpRepository

class SignUpUseCase(private val signUpRepository: SignUpRepository) {
    suspend operator fun invoke(email: String, password: String) =
        signUpRepository.signUp(email, password)

}