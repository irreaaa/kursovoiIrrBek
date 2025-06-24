package com.example.route

import com.example.dto.request.OrderConfirmationRequest
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.orderRoute() {
    post("/send-confirmation") {
        try {
            val request = call.receive<OrderConfirmationRequest>()
            println("Email request received: ${request.email}\n${request.orderDetails}")

            println("Отправка письма подтверждения заказа на ${request.email} с данными: ${request.orderDetails}")

            call.respondText("Confirmation email sent", status = HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to (e.message ?: "Unknown error")))
        }
    }
}