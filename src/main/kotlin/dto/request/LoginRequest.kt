package com.example.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val userName: String,
    val email: String,
    val password: String
)