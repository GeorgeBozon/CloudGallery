package ru.khubulty.networkImpl.data.mapper

import io.github.jan.supabase.auth.user.UserInfo
import ru.khubulty.networkApi.domain.UserInfoDomain
import javax.inject.Inject
import kotlin.time.Instant

class UserInfoMapper @Inject constructor() {
    fun mapToDomainUserInfo(authUserInfo: UserInfo) = UserInfoDomain(
        id = authUserInfo.id,
        email = authUserInfo.email,
        createdAt = authUserInfo.createdAt?.let {
            Instant.fromEpochSeconds(it.toEpochMilliseconds())
        },
        phone = authUserInfo.phone
    )
}