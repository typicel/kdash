package me.enzottic

import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import me.enzottic.modules.db.configureDatabase
import me.enzottic.modules.db.initFiles

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    initFiles()
    configureCors()
    configureSerialization()
    configureDatabase()
}

fun Application.configureCors() {
    install(CORS) {
        anyHost()
    }
}
