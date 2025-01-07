package com.gmail.vincent031525.data.data_source.dao

import com.gmail.vincent031525.data.data_source.entity.ManagerEntity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ManagerDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ManagerDao>(ManagerEntity)

    var username by ManagerEntity.username
    var password by ManagerEntity.password
    var isAdmin by ManagerEntity.isAdmin
}