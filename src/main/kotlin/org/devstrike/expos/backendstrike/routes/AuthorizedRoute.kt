package org.devstrike.expos.backendstrike.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.devstrike.expos.backendstrike.domain.models.Endpoint
import org.devstrike.expos.backendstrike.domain.models.ApiResponse


fun Route.authorizedRoute(){
    authenticate("auth-session") {
        get(Endpoint.Authorized.path) {
            call.respond(
                message = ApiResponse(success = true, null),
                status = HttpStatusCode.OK
            )
        }

    }
}