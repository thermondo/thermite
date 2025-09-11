package de.thermondo.thermite.providers.android

import de.thermondo.thermite.extensions.toOptions
import de.thermondo.thermite.providers.DriverProvider
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver

/**
 * A [DriverProvider] implementation for creating AndroidDriver instances.
 * It uses UiAutomator2Options to configure the driver based on the provided capabilities.
 */
class AndroidDriverProvider : DriverProvider {
    /**
     * Creates an [AndroidDriver] instance using the provided [Capabilities].
     *
     * @param capabilities The desired capabilities for the AndroidDriver.
     * @return An AndroidDriver instance configured with the given capabilities.
     */
    override fun createDriver(capabilities: Capabilities): WebDriver =
        AndroidDriver(
            url,
            capabilities.toOptions { UiAutomator2Options() },
        )
}
