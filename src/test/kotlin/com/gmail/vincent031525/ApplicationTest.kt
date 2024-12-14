package com.gmail.vincent031525

import io.ktor.server.testing.*
import kotlin.test.Test

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }
    }

}
