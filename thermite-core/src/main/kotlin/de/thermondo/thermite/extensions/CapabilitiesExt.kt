package de.thermondo.thermite.extensions

import io.appium.java_client.android.options.UiAutomator2Options
import org.openqa.selenium.Capabilities

fun Capabilities.toOptions(): UiAutomator2Options {
    val options = UiAutomator2Options()

    getCapability("apk")?.let {
        options.setApp(it.toString())
    }
    getCapability("automationName")?.let {
        options.setAutomationName(it.toString())
    }
    getCapability("platformName")?.let {
        options.setPlatformName(it.toString())
    }
    getCapability("deviceName")?.let {
        options.setDeviceName(it.toString())
    }
    getCapability("platformVersion")?.let {
        options.setPlatformVersion(it.toString())
    }

    return options.merge(this) as UiAutomator2Options
}
