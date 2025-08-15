package de.thermondo.thermite.providers

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.appium.SelenideAppium
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.MutableCapabilities

interface TestProvider {
    val driverProvider: String
    val capabilities: MutableCapabilities

    val torch: TorchProvider
        get() = TorchProvider

    @BeforeEach
    fun setup() {
        Configuration.browser = driverProvider
        Configuration.browserSize = null
        Configuration.browserCapabilities = capabilities
        SelenideAppium.launchApp()
    }

    @AfterEach
    fun teardown() {
        Selenide.closeWebDriver()
    }
}
