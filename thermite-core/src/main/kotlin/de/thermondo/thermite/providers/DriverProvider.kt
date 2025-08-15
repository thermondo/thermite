package de.thermondo.thermite.providers

import com.codeborne.selenide.WebDriverProvider
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import java.net.URI
import java.net.URL

interface DriverProvider : WebDriverProvider {
    val url: URL
        get() = URI("http://localhost:4723/").toURL()

    override fun createDriver(capabilities: Capabilities): WebDriver = throw NotImplementedError()
}
