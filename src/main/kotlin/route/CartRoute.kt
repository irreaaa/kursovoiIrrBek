package com.example.route

import com.example.dto.response.ErrorResponse
import com.example.model.DataRepository
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.cartRoute(){
    authenticate("auth-jwt"){
        get("/cart"){
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", Int::class) ?: return@get call.respond(
                HttpStatusCode.Unauthorized,
                ErrorResponse("Unauthorized", 401)
            )

            val user = DataRepository.userList.find { it.userId == userId } ?: return@get call.respond(
                HttpStatusCode.NotFound,
                ErrorResponse("User not found", 404)
            )

            val cart = DataRepository.sneakerList.filter { it.id in user.cartSneakerIds }
            call.respond(cart)
        }

        post("/cart/{sneakerId}"){
            val sneakerId = call.parameters["sneakerId"]?.toIntOrNull() ?: return@post call.respond(
                HttpStatusCode.BadRequest,
                ErrorResponse("Invalid sneaker ID", 400)
            )

            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", Int::class) ?: return@post call.respond(
                HttpStatusCode.Unauthorized,
                ErrorResponse("Unauthorized", 401)
            )

            val userIndex = DataRepository.userList.indexOfFirst { it.userId == userId }
            if(userIndex == -1) return@post call.respond(
                HttpStatusCode.NotFound,
                ErrorResponse("User not found", 404)
            )

            if(!DataRepository.sneakerList.any {it.id == sneakerId}) return@post call.respond(
                HttpStatusCode.NotFound,
                ErrorResponse("Sneaker not found", 404)
            )

            val currentCart = DataRepository.userList[userIndex].cartSneakerIds
            if(currentCart.contains(sneakerId)){
                call.respond(
                    HttpStatusCode.Conflict,
                    ErrorResponse("Sneaker already in cart", 409)
                )
                return@post
            }

            val updatedUser = DataRepository.userList[userIndex].copy(
                cartSneakerIds = currentCart+sneakerId
            )
            DataRepository.userList[userIndex] = updatedUser
            call.respond(
                HttpStatusCode.OK,
                mapOf("message" to "Sneaker added to cart")
            )
        }

        delete("/cart/{sneakerId}"){
            val sneakerId = call.parameters["sneakerId"]?.toIntOrNull() ?: return@delete call.respond(
                HttpStatusCode.BadRequest,
                ErrorResponse("Invalid sneaker ID", 400)
            )

            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", Int::class) ?: return@delete call.respond(
                HttpStatusCode.Unauthorized,
                ErrorResponse("Unauthorized", 401)
            )

            val userIndex = DataRepository.userList.indexOfFirst { it.userId == userId }
            if(userIndex == -1) return@delete call.respond(
                HttpStatusCode.NotFound,
                ErrorResponse("User not found", 404)
            )

            val updatedUser = DataRepository.userList[userIndex].copy(
                cartSneakerIds = DataRepository.userList[userIndex].cartSneakerIds - sneakerId
            )
            DataRepository.userList[userIndex] = updatedUser
            call.respond(
                mapOf("message" to "Sneaker removed from cart")
            )
        }
    }
}