package com.gmail.vincent031525.data.data_source.dao

import com.gmail.vincent031525.data.data_source.entity.MemberEntity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MemberDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MemberDao>(MemberEntity)

    var name by MemberEntity.name
    var email by MemberEntity.email
    var password by MemberEntity.password
    var removed by MemberEntity.removed
}