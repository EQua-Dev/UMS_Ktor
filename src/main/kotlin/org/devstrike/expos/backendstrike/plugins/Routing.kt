package org.devstrike.expos.backendstrike.plugins

import io.ktor.server.routing.*

import io.ktor.server.application.*
import org.devstrike.expos.backendstrike.domain.repository.UserDataSource
import org.devstrike.expos.backendstrike.routes.*
import org.koin.java.KoinJavaComponent.inject

fun Application.configureRouting() {
    routing {
        val userDataSource: UserDataSource by inject(UserDataSource::class.java)
        rootRoute()
        tokenVerificationRoute(application, userDataSource)
        getUserInfoRoute(application, userDataSource)
        updateUserRoute(application, userDataSource)
        deleteUserRoute(application, userDataSource)
        signOutRoute()
        authorizedRoute()
        unauthorizedRoute()
    }
}
