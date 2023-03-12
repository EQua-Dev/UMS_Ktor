package org.devstrike.expos.backendstrike

import io.ktor.server.application.*
import org.devstrike.expos.backendstrike.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureKoin() //call first
    configureAuth() //call before routing
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureSession()
}
