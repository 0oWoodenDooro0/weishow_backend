package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.MemberDao
import com.gmail.vincent031525.data.data_source.entity.MemberEntity
import com.gmail.vincent031525.domain.model.LoginRequest
import com.gmail.vincent031525.domain.model.MemberDto
import com.gmail.vincent031525.domain.repository.MemberRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and

class MemberRepositoryImpl : MemberRepository {
    override suspend fun addMember(memberDto: MemberDto): Int = query {
        MemberDao.new {
            name = memberDto.name
            email = memberDto.email
            password = memberDto.password
            gender = memberDto.gender
        }.id.value
    }

    override suspend fun updateMember(id: Int, memberDto: MemberDto) {
        query {
            MemberDao.findByIdAndUpdate(id) {
                it.name = memberDto.name
                it.email = memberDto.email
                it.password = memberDto.password
                it.gender = memberDto.gender
            }
        }
    }

    override suspend fun getMemberByEmailAndPassword(loginRequest: LoginRequest): MemberDto? = query {
        MemberDao.find((MemberEntity.email eq loginRequest.email) and (MemberEntity.password eq loginRequest.password))
            .map {
                MemberDto(
                    id = it.id.value,
                    name = it.name,
                    email = it.email,
                    password = it.password,
                    gender = it.gender
                )
            }.singleOrNull()
    }
}