package ru.khubulty.networkImpl.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.FlowType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import ru.khubulty.networkApi.data.AuthApi
import ru.khubulty.networkImpl.BuildConfig
import ru.khubulty.networkImpl.data.AuthApiImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule{

    @Binds
    @Singleton
    fun bindAuthApi(authImpl: AuthApiImpl): AuthApi

    companion object{

        @Provides
        @Singleton
        internal fun provideSupabaseClient(): SupabaseClient =
            createSupabaseClient(BuildConfig.SUPABASE_URL, BuildConfig.SUPABASE_PUBLISHABLE_KEY){
                install(Postgrest)
                install(Auth) {
                    flowType = FlowType.IMPLICIT
                    scheme = "app"
                    host = "supabase.com"
                }
                install(Storage)
            }

        @Provides
        @Singleton
        internal fun provideAuth(client: SupabaseClient): Auth = client.auth

        @Provides
        @Singleton
        internal fun providePostgrest(client: SupabaseClient): Postgrest = client.postgrest

        @Provides
        @Singleton
        internal fun providePostgres(client: SupabaseClient) = client.pluginManager

        @Provides
        @Singleton
        internal fun provideStorage(client: SupabaseClient): Storage = client.storage
    }
}