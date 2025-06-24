package com.example

import authRoute
import com.example.route.cartRoute
import com.example.route.favoritesRoute
import com.example.route.orderRoute
import com.example.route.sneakersRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        authRoute()
        sneakersRoute()
        favoritesRoute()
        cartRoute()
        orderRoute()
    }
}