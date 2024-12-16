package com.gmail.vincent031525.config.plugin

import com.gmail.vincent031525.data.data_source.entity.*
import com.gmail.vincent031525.data.repository.*
import com.gmail.vincent031525.domain.repository.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.postgresql.ds.PGSimpleDataSource

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(module {
            singleOf(::provideDatabase)
            singleOf(::ManagementRepositoryImpl) { bind<ManagementRepository>() }
            singleOf(::ManagerRepositoryImpl) { bind<ManagerRepository>() }
            singleOf(::MemberRepositoryImpl) { bind<MemberRepository>() }
            singleOf(::MovieRepositoryImpl) { bind<MovieRepository>() }
            singleOf(::ScreenRepositoryImpl) { bind<ScreenRepository>() }
            singleOf(::SeatRepositoryImpl) { bind<SeatRepository>() }
            singleOf(::SessionRepositoryImpl) { bind<SessionRepository>() }
            singleOf(::TheaterRepositoryImpl) { bind<TheaterRepository>() }
            singleOf(::TicketRepositoryImpl) { bind<TicketRepository>() }
        })
    }
    val database by inject<Database>()
    transaction(database) {
        SchemaUtils.createMissingTablesAndColumns(ContentRatingEntity)
        SchemaUtils.createMissingTablesAndColumns(MovieEntity)
        SchemaUtils.createMissingTablesAndColumns(TheaterEntity)
        SchemaUtils.createMissingTablesAndColumns(ManagerEntity)
        SchemaUtils.createMissingTablesAndColumns(ManagementEntity)
        SchemaUtils.createMissingTablesAndColumns(ScreenEntity)
        SchemaUtils.createMissingTablesAndColumns(SessionEntity)
        SchemaUtils.createMissingTablesAndColumns(MemberEntity)
        SchemaUtils.createMissingTablesAndColumns(TicketStatusEntity)
        SchemaUtils.createMissingTablesAndColumns(SeatEntity)
        SchemaUtils.createMissingTablesAndColumns(TicketTypeEntity)
        SchemaUtils.createMissingTablesAndColumns(TicketEntity)
    }
}

fun provideDatabase(): Database {
    val dataSource = PGSimpleDataSource().apply {
        user = "postgres"
        password = "postgres"
        databaseName = "weishow"
    }
    return Database.connect(dataSource)
}