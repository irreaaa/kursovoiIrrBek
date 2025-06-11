package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthDto (
    val userId: Int,
    val userName: String,
    val email: String,
    val password: String,
)