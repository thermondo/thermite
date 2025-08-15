package de.thermondo.thermite.providers.android

import de.thermondo.thermite.providers.TestProvider

abstract class AndroidTestProvider : TestProvider {
    override val driverProvider: String = AndroidDriverProvider::class.java.name
}
