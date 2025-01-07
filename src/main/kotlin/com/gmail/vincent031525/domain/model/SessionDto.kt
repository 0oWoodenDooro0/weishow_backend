package com.gmail.vincent031525.domain.model

import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class SessionDto(val id: Int, val startTime: LocalTime, val screenId: Int, val movieId: Int)
