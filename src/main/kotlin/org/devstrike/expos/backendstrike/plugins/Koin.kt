package org.devstrike.expos.backendstrike.plugins

import io.ktor.server.application.*
import org.devstrike.expos.backendstrike.di.koinModule
import org.koin.ktor.plugin.Koin

fun Application.configureKoin(){
    install(Koin){
        modules(koinModule)
    }
}