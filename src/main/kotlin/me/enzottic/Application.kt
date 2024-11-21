package me.enzottic

import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import me.enzottic.db.configureDatabase

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureCors()
    configureSerialization()
    configureDatabase()
}

fun Application.configureCors() {
    install(CORS) {
        anyHost()
    }
}
