package org.devstrike.expos.backendstrike.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.devstrike.expos.backendstrike.domain.models.ApiResponse
import org.devstrike.expos.backendstrike.domain.models.Endpoint
import org.devstrike.expos.backendstrike.domain.models.UserSession
import org.devstrike.expos.backendstrike.domain.repository.UserDataSource

fun Route.getUserInfoRoute(
    app: Application,
    userDataSource: UserDataSource
){
    authenticate("auth-session") {
        get (Endpoint.GetUserInfo.path){
            val userSession = call.principal<UserSession>()
            if (userSession == null){
                app.log.info("INVALID SESSION")
                call.respondRedirect(Endpoint.Unauthorized.path)
            }else{
                try {
                    call.respond(
                        message = ApiResponse(
                            success = true,
                            user = userDataSource.getUserInfo(userId = userSession.id)
                        ),
                        status = HttpStatusCode.OK
                    )
                }catch (e: Exception){

                }
            }
        }
    }
}