package me.enzottic.models

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseLinkRepository(database: Database): LinkRepository {
    object Links : Table() {
        val name = varchar("name", 256)
        val url = varchar("url", 256)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Links)
        }
    }

    override suspend fun allLinks(): List<Link> = dbQuery {
        Links
            .selectAll()
            .map { Link(it[Links.name], it[Links.url]) }
    }

    override suspend fun addLink(link: Link): Unit = dbQuery {
        Links.insert {
            it[name] = link.name
            it[url] = link.url
        }
    }

    override suspend fun removeLink(name: String): Boolean = dbQuery {
       true
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }