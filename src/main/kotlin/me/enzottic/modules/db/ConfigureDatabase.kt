package me.enzottic.modules.db

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.enzottic.models.Link
import org.jetbrains.exposed.sql.Database
import java.nio.file.Paths

fun Application.initFiles() {
    // Create files.db file at $HOME/.config/kdash/
    val home = Paths.get(System.getProperty("user.home")).toString()
    val configDir = Paths.get(home, ".config", "kdash").toFile()
    if (!configDir.exists()) {
        configDir.mkdirs()
    }

    val linksDb = Paths.get(home, ".config", "kdash", "links.db").toFile()
    if (!linksDb.exists()) {
        linksDb.createNewFile()
    }
}

fun Application.configureDatabase() {
    val home = Paths.get(System.getProperty("user.home")).toString()

    val database = Database.connect(
        "jdbc:sqlite:${home}/.config/kdash/links.db",
        "org.sqlite.JDBC"
    )

    val linksRepo = DashboardDatabase(database)

    routing {
        get("/allLinks") {
            val links = linksRepo.allLinks()
            call.respond(links)
        }

        post("/links") {
            val link = call.receive<Link>()
            linksRepo.addLink(link)
            call.respond(HttpStatusCode.Created)
        }
    }
}
