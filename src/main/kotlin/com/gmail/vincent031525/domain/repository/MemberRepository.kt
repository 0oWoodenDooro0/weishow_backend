package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.request.LoginRequest
import com.gmail.vincent031525.domain.model.MemberDto
import com.gmail.vincent031525.domain.model.request.RegisterRequest
import com.gmail.vincent031525.domain.model.request.UpdateMemberRequest
import com.gmail.vincent031525.domain.model.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface MemberRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun getAllMembers(): Result<List<MemberDto>>
    suspend fun updateMember(id: Int, updateMemberRequest: UpdateMemberRequest) : Result<Int>
    suspend fun register(registerRequest: RegisterRequest): Result<Int>
    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse>
}