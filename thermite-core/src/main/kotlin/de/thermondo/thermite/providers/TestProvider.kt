package de.thermondo.thermite.providers

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.appium.SelenideAppium
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.MutableCapabilities

/**
 * A test provider interface that sets up and tears down the Selenide Appium environment for each test.
 */
interface TestProvider {
    val driverProvider: String
    val capabilities: MutableCapabilities

    val torch: TorchProvider
        get() = TorchProvider

    /**
     * Sets up the Selenide Appium environment before each test.
     * Configures the browser and capabilities, and launches the app.
     */
    @BeforeEach
    fun setup() {
        Configuration.browser = driverProvider
        Configuration.browserSize = null
        Configuration.browserCapabilities = capabilities
        SelenideAppium.launchApp()
    }

    /**
     * Tears down the Selenide Appium environment after each test.
     * Closes the WebDriver instance.
     */
    @AfterEach
    fun teardown() {
        Selenide.closeWebDriver()
    }
}
