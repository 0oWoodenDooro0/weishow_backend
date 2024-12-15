package com.gmail.vincent031525.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(val code: Int, val message: String)
