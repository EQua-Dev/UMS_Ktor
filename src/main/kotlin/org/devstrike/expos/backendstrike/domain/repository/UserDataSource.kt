package org.devstrike.expos.backendstrike.domain.repository

import org.devstrike.expos.backendstrike.domain.models.User

interface UserDataSource {

    suspend fun getUserInfo(userId: String): User?
    suspend fun saveUserInfo(user: User): Boolean
    suspend fun deleteUser(userId: String): Boolean
    suspend fun updateUserInfo(
        userId: String,
        firstName: String,
        lastName: String
    ): Boolean

}