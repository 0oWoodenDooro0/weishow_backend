package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.DataSource
import com.gmail.vincent031525.domain.model.MemberDto
import com.gmail.vincent031525.domain.repository.MemberRepository

class MemberRepositoryImpl(private val dataSource: DataSource) : MemberRepository {
    override suspend fun addMember(memberDto: MemberDto): Int = dataSource.addMember(memberDto)
}