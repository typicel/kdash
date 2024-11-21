package me.enzottic.db

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.enzottic.models.DatabaseLinkRepository
import me.enzottic.models.Link
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase() {
    val database = Database.connect(
        "jdbc:sqlite:C:/Users/tyler/Projects/dashboard/dash/src/main/kotlin/me/enzottic/db/links.db",
        "org.sqlite.JDBC"
    )

    val linksRepo = DatabaseLinkRepository(database)

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
