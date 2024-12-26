package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.request.LoginRequest
import com.gmail.vincent031525.domain.model.MemberDto
import com.gmail.vincent031525.domain.model.response.LoginResponse
import io.ktor.http.cio.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface MemberRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun getAllMembers(): Result<List<MemberDto>>
    suspend fun addMember(memberDto: MemberDto): Int
    suspend fun updateMember(id: Int, memberDto: MemberDto)
    suspend fun getMemberByEmailAndPassword(loginRequest: LoginRequest): Result<LoginResponse>
}