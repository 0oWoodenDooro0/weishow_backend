package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.sql.Table

object MemberEntity : Table(name = "member") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    val email = varchar("email", 50)
    val password = varchar("password", 50)
    val gender = varchar("gender", 50)
    override val primaryKey = PrimaryKey(id)
}