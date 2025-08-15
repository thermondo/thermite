package de.thermondo.thermite

import com.codeborne.selenide.Condition.visible
import de.thermondo.thermite.providers.android.AndroidTestProvider
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.MobileBrowserType
import org.junit.jupiter.api.Test
import org.openqa.selenium.MutableCapabilities

class SampleTest : AndroidTestProvider() {
    override val capabilities: MutableCapabilities =
        MutableCapabilities().apply {
            setCapability("apk", "thermite-core/src/test/resources/curiosity-debug.apk")
            setCapability("automationName", AutomationName.ANDROID_UIAUTOMATOR2)
            setCapability("platformName", MobileBrowserType.ANDROID)
        }

    @Test
    fun testSample() {
        torch
            .findsTextEqualTo("Button")
            .shouldBe(visible)
    }
}
