package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.MemberDao
import com.gmail.vincent031525.data.data_source.entity.MemberEntity
import com.gmail.vincent031525.domain.model.MemberDto
import com.gmail.vincent031525.domain.model.request.MemberLoginRequest
import com.gmail.vincent031525.domain.model.request.RegisterRequest
import com.gmail.vincent031525.domain.model.request.UpdateMemberRequest
import com.gmail.vincent031525.domain.model.response.LoginResponse
import com.gmail.vincent031525.domain.repository.MemberRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and

class MemberRepositoryImpl : MemberRepository {
    override suspend fun getAllMembers(): Result<List<MemberDto>> = try {
        query {
            val members = MemberDao.find(MemberEntity.removed eq false).map {
                MemberDto(
                    it.id.value, it.name, it.email, it.password
                )
            }
            Result.success(members)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun updateMember(id: Int, updateMemberRequest: UpdateMemberRequest): Result<Int> = try {
        query {
            MemberDao.findByIdAndUpdate(id) { member ->
                updateMemberRequest.email?.let { member.email = it }
                updateMemberRequest.name?.let { member.name = it }
                updateMemberRequest.password?.let { member.password = it }
            }
            Result.success(id)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun deleteMember(id: Int): Result<Int> = try {
        query {
            MemberDao.findByIdAndUpdate(id) { member ->
                member.removed = true
            }
            Result.success(id)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }


    override suspend fun register(registerRequest: RegisterRequest): Result<Int> = try {
        query {
            val id = MemberDao.new {
                name = registerRequest.name
                email = registerRequest.email
                password = registerRequest.password
            }.id.value
            Result.success(id)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun login(loginRequest: MemberLoginRequest): Result<LoginResponse> = try {
        query {
            val result =
                MemberDao.find((MemberEntity.email eq loginRequest.email) and (MemberEntity.password eq loginRequest.password))
                    .map { LoginResponse(id = it.id.value, name = it.name, email = it.email) }.single()
            Result.success(result)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}