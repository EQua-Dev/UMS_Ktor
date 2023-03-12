package org.devstrike.expos.backendstrike.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UserUpdate(
    val firstName: String,
    val lastName: String
)
