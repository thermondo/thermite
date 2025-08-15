package de.thermondo.thermite.providers

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.openqa.selenium.Capabilities

class DriverProviderTest {
    private val capabilities: Capabilities = mockk()
    private val provider =
        object : DriverProvider {
            // No overrides; use defaults from the interface
        }

    @Test
    fun testUrl() {
        assertEquals("http://localhost:4723/", provider.url.toString())
    }

    @Test
    fun testCreateDriver() {
        assertThrows<NotImplementedError> {
            provider.createDriver(capabilities)
        }
    }
}
