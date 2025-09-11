package de.thermondo.thermite.providers.android

import de.thermondo.thermite.providers.TestProvider

/**
 * An abstract [TestProvider] implementation for Android tests.
 * It sets the [driverProvider] to [AndroidDriverProvider].
 */
abstract class AndroidTestProvider : TestProvider {
    /**
     * The driver provider class name for Android tests.
     */
    override val driverProvider: String = AndroidDriverProvider::class.java.name
}
