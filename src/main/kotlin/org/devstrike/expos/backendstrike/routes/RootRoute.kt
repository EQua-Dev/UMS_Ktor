package org.devstrike.expos.backendstrike.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import org.devstrike.expos.backendstrike.domain.models.Endpoint

fun Routing.rootRoute(){
    get(Endpoint.Root.path) {
        call.respondText("Welcome to ktor server!")
    }
}