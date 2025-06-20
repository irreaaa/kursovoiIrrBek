package com.example.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String,
    val userId: Int,
    val userName: String,
    val email: String
)