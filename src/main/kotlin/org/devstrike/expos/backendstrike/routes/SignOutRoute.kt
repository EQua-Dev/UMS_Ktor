package org.devstrike.expos.backendstrike.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.devstrike.expos.backendstrike.domain.models.ApiResponse
import org.devstrike.expos.backendstrike.domain.models.Endpoint
import org.devstrike.expos.backendstrike.domain.models.UserSession

fun Route.signOutRoute(){
    authenticate ("auth-session"){
        get (Endpoint.SignOut.path){
            call.sessions.clear<UserSession>()
            call.respond(
                message = ApiResponse(
                    success = true
                ),
                status = HttpStatusCode.OK
            )
        }
    }
}