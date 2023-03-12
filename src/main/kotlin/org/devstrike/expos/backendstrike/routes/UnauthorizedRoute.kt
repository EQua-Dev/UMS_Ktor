package org.devstrike.expos.backendstrike.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.devstrike.expos.backendstrike.domain.models.Endpoint

fun Route.unauthorizedRoute(){
    //this is the route that the user will be redirected to if not authorized to access a route
    get(Endpoint.Unauthorized.path){
        call.respond(
            message = "Not Authorized.",
            status = HttpStatusCode.Unauthorized
        )
    }
}