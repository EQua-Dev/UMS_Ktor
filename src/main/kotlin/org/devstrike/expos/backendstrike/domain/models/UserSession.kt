package org.devstrike.expos.backendstrike.domain.models

import io.ktor.server.auth.*

//data class to be used to store the user session details
//Principal is extended to enable authentication using this class
data class UserSession(
    val id: String,
    val name: String
) : Principal
