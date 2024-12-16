package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.LoginRequest
import com.gmail.vincent031525.domain.model.MemberDto
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface MemberRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun addMember(memberDto: MemberDto): Int
    suspend fun updateMember(id: Int, memberDto: MemberDto)
    suspend fun getMemberByEmailAndPassword(loginRequest: LoginRequest): MemberDto?
}