package me.enzottic.models

interface LinkRepository {
    suspend fun allLinks(): List<Link>
    suspend fun addLink(link: Link)
    suspend fun removeLink(name: String): Boolean
}
