package com.gmail.vincent031525.config.plugin

import com.gmail.vincent031525.data.data_source.DataSource
import com.gmail.vincent031525.data.repository.*
import com.gmail.vincent031525.domain.repository.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.postgresql.ds.PGSimpleDataSource

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(module {
            singleOf(::provideDatabase)
            singleOf(::DataSource)
            singleOf(::MemberRepositoryImpl) { bind<MemberRepository>() }
            singleOf(::MovieRepositoryImpl) { bind<MovieRepository>() }
            singleOf(::ScreenRepositoryImpl) { bind<ScreenRepository>() }
            singleOf(::SeatRepositoryImpl) { bind<SeatRepository>() }
            singleOf(::SessionRepositoryImpl) { bind<SessionRepository>() }
            singleOf(::TheaterRepositoryImpl) { bind<TheaterRepository>() }
            singleOf(::TicketRepositoryImpl) { bind<TicketRepository>() }
        })
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