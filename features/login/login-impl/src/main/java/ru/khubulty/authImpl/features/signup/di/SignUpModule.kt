package ru.khubulty.authImpl.features.signup.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.khubulty.authImpl.features.signup.data.repo.SignUpRepositoryImpl
import ru.khubulty.authImpl.features.signup.domain.repo.SignUpRepository
import ru.khubulty.authImpl.features.signup.domain.usecase.SignUpUseCase

@Module
@InstallIn(ViewModelComponent::class)
internal interface SignUpModule {

    @Binds
    @ViewModelScoped
    fun bindSignUpRepository(signUpRepository: SignUpRepositoryImpl): SignUpRepository

    companion object{

        @Provides
        @ViewModelScoped
        fun provideSignUpUseCase(signUpRepository: SignUpRepository): SignUpUseCase = SignUpUseCase(signUpRepository)
    }
}