package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object MemberEntity : IntIdTable(name = "member") {
    val name = varchar("name", 50)
    val email = varchar("email", 50)
    val password = varchar("password", 50)
    val gender = varchar("gender", 50)
}