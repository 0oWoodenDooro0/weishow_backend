package com.gmail.vincent031525.config.plugin

import com.gmail.vincent031525.data.data_source.LocalDataSource
import com.gmail.vincent031525.data.repository.MovieRepositoryImpl
import com.gmail.vincent031525.domain.repository.MovieRepository
import com.gmail.vincent031525.domain.service.MovieService
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
            singleOf(::LocalDataSource)
            singleOf(::MovieRepositoryImpl) { bind<MovieRepository>() }
            singleOf(::MovieService)
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