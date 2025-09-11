package de.thermondo.thermite.extensions

import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.remote.options.BaseOptions
import org.openqa.selenium.Capabilities

/**
 * Converts [Capabilities] to specific [BaseOptions] type using the provided [factory] function.
 * It maps common capabilities to the corresponding options properties.
 *
 * @param factory A function that creates an instance of the desired [BaseOptions] type.
 * @return An instance of the specified [BaseOptions] type with properties set from the capabilities.
 */
fun <T : BaseOptions<T>> Capabilities.toOptions(factory: () -> T): T {
    val options = factory()

    when (options) {
        is UiAutomator2Options -> {
            getCapability("apk")?.let { options.setApp(it.toString()) }
            getCapability("deviceName")?.let {
                options.setDeviceName(it.toString())
            }
        }
    }
    getCapability("automationName")?.let {
        options.setAutomationName(it.toString())
    }
    getCapability("platformName")?.let {
        options.setPlatformName(it.toString())
    }
    getCapability("platformVersion")?.let {
        options.setPlatformVersion(it.toString())
    }
    return options.merge(this) as T
}
