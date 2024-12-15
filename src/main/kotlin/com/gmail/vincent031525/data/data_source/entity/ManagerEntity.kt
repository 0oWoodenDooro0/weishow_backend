package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.sql.Table

object ManagerEntity : Table(name = "manager") {
    val id = integer("id").autoIncrement()
    var username = varchar("username", 50)
    var password = varchar("password", 50)
    override val primaryKey = PrimaryKey(id)
}