package me.enzottic.models

import kotlinx.serialization.Serializable

@Serializable
data class Link(val name: String, val url: String)
