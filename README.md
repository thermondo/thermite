# Thermite 🔥

[![Build Status](https://github.com/thermondo/thermite/actions/workflows/main.yml/badge.svg?event=push)](https://github.com/thermondo/thermite/actions)
[![codecov](https://codecov.io/gh/thermondo/thermite/graph/badge.svg?token=L9PY0DEeYP)](https://codecov.io/gh/thermondo/thermite)
[![License](https://img.shields.io/dub/l/vibe-d.svg)](LICENSE)
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)
[![](https://img.shields.io/maven-central/v/de.thermondo/thermite?color=blue)](https://search.maven.org/search?q=de.thermonod)

## About

Thermite is an automation testing library for mobile. It
leverages [Appium](https://appium.io/) and [Selenide](https://selenide.org/).

### Getting Started

### Gradle Setup

```kotlin
dependencies {
    testImplementation("de.thermondo:thermite:0.1.1")
}
```

### Leveraging Test Providers

You can create a test by extending TestProvider and providing your device
specifications:

```kotlin

class ExampleTest : AndroidTestProvider() {
    override val capabilities: MutableCapabilities
        get() = MutableCapabilities().apply {
            setCapability("automationName", AutomationName.ANDROID_UIAUTOMATOR2)
            setCapability("platformName", MobileBrowserType.ANDROID)
        }

    @Test
    fun testLogin() {
        // your test code here
    }
}
```

### Using Torch 🔥

You can use torch to easily find elements:

```kotlin
torch
    .findTextEqualTo("Button")
    .shouldBe(visible)
```
