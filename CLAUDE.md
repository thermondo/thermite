# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Thermite is a mobile automation testing library built on top of Appium and Selenide. It provides a Kotlin DSL for writing mobile tests with a focus on readability and ease of use.

**Key Technologies:**
- Kotlin (version 2.0.0)
- Appium (Java client 10.0.0)
- Selenide Appium (7.12.0)
- JUnit Jupiter (6.0.1)
- Gradle with Kotlin DSL

## Build Commands

The project uses a Makefile as the primary interface for all build operations:

- `make clean` - Clean build artifacts
- `make format` - Format Kotlin code using kotlinter
- `make lint` - Run linting (kotlinter + detekt)
- `make test` - Run all tests
- `make report` - Generate code coverage reports (koverHtmlReport, koverXmlReport)
- `make assemble` - Build the library
- `make local` - Build and publish to Maven Local for testing
- `make all` - Run clean, format, lint, test, report, and assemble

**Running a single test:**
```bash
./gradlew test --tests "de.thermondo.thermite.AndroidSampleTest.testSample"
```

**Appium Management:**
- `make appium-start` - Start Appium server (logs to appium/appium.log, PID stored in appium/appium.pid)
- `make appium-stop` - Stop Appium server

## Project Structure

The project is a multi-module Gradle build:
- **thermite-core** - Main library module containing all production and test code
  - `src/main/kotlin/de/thermondo/thermite/` - Library source code
    - `providers/` - Test and driver providers
    - `extensions/` - Kotlin extensions for capabilities
  - `src/test/kotlin/de/thermondo/thermite/` - Test code

## Architecture

### Provider Pattern

The library uses a provider-based architecture:

1. **TestProvider** (interface at `providers/TestProvider.kt`) - Base interface that all test classes implement
   - Handles test lifecycle (@BeforeEach setup, @AfterEach teardown)
   - Configures Selenide with driver provider and capabilities
   - Provides access to `torch` for element finding

2. **AndroidTestProvider** (abstract class at `providers/android/AndroidTestProvider.kt`) - Android-specific test provider
   - Extends TestProvider
   - Sets driver provider to AndroidDriverProvider
   - Test classes extend this to write Android tests

3. **DriverProvider** (interface at `providers/DriverProvider.kt`) - Creates WebDriver instances
   - Defines default Appium server URL (http://localhost:4723/)
   - AndroidDriverProvider implementation creates AndroidDriver with UiAutomator2Options

### Torch API

**TorchProvider** (object at `providers/TorchProvider.kt`) - The main API for finding elements
- `find(selector: String)` - Find by XPath
- `findTextEqualTo(text: String)` - Find by exact text match
- Returns SelenideElement for chaining with Selenide conditions

**Usage pattern:**
```kotlin
torch
    .findTextEqualTo("Button")
    .shouldBe(visible)
```

### Test Structure

Tests follow this pattern:
1. Extend `AndroidTestProvider`
2. Override `capabilities` to specify app path and Appium settings
3. Use `torch` object to find elements
4. Use Selenide conditions for assertions

Example: `thermite-core/src/test/kotlin/de/thermondo/thermite/AndroidSampleTest.kt`

## Code Quality

The project enforces strict code quality standards:
- **kotlinter** - Kotlin code formatting (auto-format with `make format`)
- **detekt** - Static analysis for Kotlin
- **kover** - Code coverage reporting

Builds will fail if code style violations are detected. Always run `make format` before committing.

## Testing Requirements

The project uses Android Emulator for integration tests. Tests require:
- Appium server running on localhost:4723
- Android emulator running (API level 35 in CI)
- UiAutomator2 driver installed

Tests are run via `make test` which internally calls `./gradlew test`.

## Publishing

The library is published to Maven Central:
- Group ID: `de.thermondo`
- Artifact ID: `thermite`
- Version controlled via `VERSION` environment variable (defaults to "local")

Publishing is handled by the vanniktech maven publish plugin with automatic signing for Maven Central releases (but not for local publishing).

## CI/CD

GitHub Actions workflow (`.github/workflows/main.yml`) runs:
1. **Assemble** - Tests build on Java 17 and 19
2. **Lint** - Runs kotlinter and detekt
3. **Test & Report** - Runs tests in Android emulator and uploads coverage to Codecov

## Branch Naming Convention

Use these prefixes for feature branches:
- `feature/` - New features
- `enhancement/` - Enhancements to existing features
- `fix/` - Bug fixes
