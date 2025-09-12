package de.thermondo.thermite.providers

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import io.appium.java_client.AppiumBy

/**
 * A provider for wrapping Selenide to create human-readable extensions for testing.
 */
object TorchProvider {
    /**
     * Finds a SelenideElement using the given XPath selector.
     *
     * @param selector The XPath selector to locate the element.
     * @return The located SelenideElement.
     */
    fun find(selector: String): SelenideElement = Selenide.`$`(AppiumBy.xpath(selector))

    /**
     * Finds a SelenideElement with the exact text.
     *
     * @param text The exact text to locate the element.
     * @return The located SelenideElement.
     */
    fun findTextEqualTo(text: String): SelenideElement = find(".//*[@text='$text']")
}
