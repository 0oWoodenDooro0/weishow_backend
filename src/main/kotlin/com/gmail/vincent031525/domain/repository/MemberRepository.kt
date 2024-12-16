package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.MemberDto

interface MemberRepository {
    suspend fun addMember(memberDto: MemberDto): Int
}