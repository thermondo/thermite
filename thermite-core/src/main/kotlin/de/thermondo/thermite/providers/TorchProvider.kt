package de.thermondo.thermite.providers

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import io.appium.java_client.AppiumBy

object TorchProvider {
    fun find(selector: String): SelenideElement = Selenide.`$`(AppiumBy.xpath(selector))

    fun findTextEqualTo(text: String): SelenideElement = find(".//*[@text='$text']")
}
