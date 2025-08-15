package de.thermondo.thermite.extensions

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.openqa.selenium.Capabilities
import org.openqa.selenium.MutableCapabilities

class CapabilitiesExtTest {
    private val capabilities: Capabilities = mockk()

    @AfterEach
    fun teardown() {
        confirmVerified(capabilities)
    }

    @Test
    fun testToCapabilitiesDefaults() {
        val options = MutableCapabilities().toOptions()

        assertTrue(options.app.isEmpty)
        assertEquals("UIAutomator2", options.automationName.get())
        assertTrue(options.deviceName.isEmpty)
        assertEquals("ANDROID", options.platformName!!.name)
        assertTrue(options.platformVersion.isEmpty)
    }

    @Test
    fun testToCapabilities() {
        every { capabilities.getCapability("apk") } returns "debug.apk"
        every { capabilities.getCapability("automationName") } returns "UiAutomator2"
        every { capabilities.getCapability("deviceName") } returns "Pixel 6"
        every { capabilities.getCapability("platformName") } returns "Android"
        every { capabilities.getCapability("platformVersion") } returns "13"
        every { capabilities.asMap() } returns emptyMap()
        every { capabilities.merge(any()) } answers { firstArg() }

        val options = capabilities.toOptions()

        assertEquals("debug.apk", options.app.get())
        assertEquals("UIAutomator2", options.automationName.get())
        assertEquals("Pixel 6", options.deviceName.get())
        assertEquals("ANDROID", options.platformName!!.name)
        assertEquals("13", options.platformVersion.get())

        verify { capabilities.getCapability("apk") }
        verify { capabilities.getCapability("automationName") }
        verify { capabilities.getCapability("deviceName") }
        verify { capabilities.getCapability("platformName") }
        verify { capabilities.getCapability("platformVersion") }
        verify(atMost = 5) { capabilities.asMap() }
    }
}
