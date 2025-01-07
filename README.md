# WeiShow

Kotlin service built with Ktor and Exposed.

## Requirements 

Kotlin 2.1 w/ Java 21 for building + running the application:

## Getting started

If you don't use AppEngine to run the application, please remove dependency in your project.

`build.gradle.kts`
```kotlin
implementation("com.google.cloud.sql:postgres-socket-factory:1.21.0")
```

Then change your database url to your own.

`config/plugin/Koin.kt`
```kotlin
fun provideDatabase(): Database {
    val url = "your_url" 
    return Database.connect(
        url = url,
        driver = "org.postgresql.Driver",
        user = "your_user",
        password = "your_password"
    )
}
```

**To get started with development run:**

```bash
./gradlew run
```

Runs embedded web server on `localhost:8080`
