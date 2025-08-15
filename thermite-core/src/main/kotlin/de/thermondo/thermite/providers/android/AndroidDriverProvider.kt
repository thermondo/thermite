package de.thermondo.thermite.providers.android

import de.thermondo.thermite.extensions.toOptions
import de.thermondo.thermite.providers.DriverProvider
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver

class AndroidDriverProvider : DriverProvider {
    override fun createDriver(capabilities: Capabilities): WebDriver = AndroidDriver(url, capabilities.toOptions())
}
