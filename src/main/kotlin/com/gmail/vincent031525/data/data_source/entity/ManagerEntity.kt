package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object ManagerEntity : IntIdTable(name = "manager") {
    var username = varchar("username", 50)
    var password = varchar("password", 50)
    var isAdmin = bool("is_admin").default(false)

    init {
        uniqueIndex(username)
    }
}