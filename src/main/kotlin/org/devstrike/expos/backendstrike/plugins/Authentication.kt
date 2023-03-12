package org.devstrike.expos.backendstrike.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import org.devstrike.expos.backendstrike.domain.models.Endpoint
import org.devstrike.expos.backendstrike.domain.models.UserSession

//function to authenticate access using user sessions
fun Application.configureAuth(){
    install(Authentication){
        session<UserSession>( name = "auth-session"){
            validate { session ->
                //returns the session info if it is valid
                session
            }
            challenge{
                //redirects the user to the unauthorized route if not validated
                call.respondRedirect(Endpoint.Unauthorized.path)
            }
        }
    }
}