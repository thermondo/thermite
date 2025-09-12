package de.thermondo.thermite.providers

import com.codeborne.selenide.WebDriverProvider
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import java.net.URI
import java.net.URL

/**
 * A base interface for creating WebDriver instances with a predefined Appium server URL.
 * Implementing classes should provide the logic to create specific WebDriver instances.
 */
interface DriverProvider : WebDriverProvider {
    val url: URL
        get() = URI("http://localhost:4723/").toURL()

    /**
     * Creates a WebDriver instance based on the provided [Capabilities].
     * This method must be implemented by subclasses to return the appropriate WebDriver.
     *
     * @param capabilities The desired capabilities for the WebDriver.
     * @return A WebDriver instance configured with the given capabilities.
     */
    override fun createDriver(capabilities: Capabilities): WebDriver = throw NotImplementedError()
}
