package de.thermondo.thermite.providers

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import io.appium.java_client.AppiumBy

object TorchProvider {
    fun finds(selector: String): SelenideElement = Selenide.`$`(AppiumBy.xpath(selector))

    fun findsTextEqualTo(text: String): SelenideElement = finds(".//*[@text='$text']")
}
