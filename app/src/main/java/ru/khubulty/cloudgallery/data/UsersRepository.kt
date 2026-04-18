package ru.khubulty.cloudgallery.data

import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.Serializable
import ru.khubulty.cloudgallery.domain.UserDomain
import javax.inject.Inject

private const val TABLE_USERS = "users"

interface UsersRepository {
    suspend fun getUsers(): List<UserDomain>
}

class UsersRepoImpl @Inject constructor(private val postgrest: Postgrest) : UsersRepository {
    override suspend fun getUsers(): List<UserDomain> =
        postgrest.from(TABLE_USERS).select().decodeList<User>().map { UserDomain(it.id, it.name) }
}

@Serializable
data class User(val id: Int, val name: String)