plugins {
    kotlin("jvm") version libs.versions.kotlin.get()
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.kover)
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "org.jmailen.kotlinter")
    apply(plugin = "org.jetbrains.kotlinx.kover")
}
