package me.enzottic.modules.weather

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

//@Serializable
//data class CurrentConditionsResponse(val)
//
//interface WeatherService {
//    suspend fun getCurrentConditions()
//    suspend fun getLocationKey(zip: String): String?
//}
//
//class RemoteWeatherService: WeatherService {
//    override suspend fun getCurrentConditions() {
//        val client = HttpClient()
//        val response = client.get("")
//    }
//
//    override suspend fun getLocationKey(zip: String): String? {
//        TODO("Not yet implemented")
//    }
//
//}
//
//fun Application.configureWeather(weatherService: WeatherService) {
//    routing {
//        get("/weather/current") {
//            val response = weatherService.getCurrentConditions()
//        }
//
//        get("/weather/location") {
//            val search = call.request.queryParameters["search"]
//            if (search == null) { call.respond(HttpStatusCode.BadRequest) }
//            else {
//
//            }
//        }
//    }
//}