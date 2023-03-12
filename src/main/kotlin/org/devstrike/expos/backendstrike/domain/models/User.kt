package org.devstrike.expos.backendstrike.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val emailAddress: String,
    val profilePhoto: String
)
