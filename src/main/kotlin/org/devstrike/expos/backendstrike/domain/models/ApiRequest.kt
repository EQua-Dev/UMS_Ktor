package org.devstrike.expos.backendstrike.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiRequest(
    val tokenId: String
)
