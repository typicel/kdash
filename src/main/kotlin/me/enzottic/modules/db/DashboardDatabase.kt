package me.enzottic.modules.db

import kotlinx.coroutines.Dispatchers
import me.enzottic.models.Link
import me.enzottic.models.LinkCategory
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class DashboardDatabase(database: Database) {
    object Links : Table() {
        val id = integer("id").autoIncrement()
        val name = varchar("name", 256)
        val url = varchar("url", 256)
        val categoryId = integer("category_id").references(LinkCategories.id)
    }

    object LinkCategories : Table() {
        val id = integer("id").autoIncrement()
        val name = varchar("name", 256)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Links)
        }
    }

    suspend fun allLinks(): List<Link> = dbQuery {
        Links
            .selectAll()
            .map { Link(it[Links.name], it[Links.url]) }
    }

    suspend fun addLink(link: Link): Unit = dbQuery {
        Links.insert {
            it[name] = link.name
            it[url] = link.url
        }
    }

    suspend fun addLinkCategory(category: LinkCategory): Unit = dbQuery {
        LinkCategories.insert {
            it[name] = category.name
        }
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }