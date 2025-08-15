plugins {
    kotlin("jvm")
}

group = "de.thermondo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.appium)
    implementation(libs.junit)
    implementation(libs.selenide.appium)

    testImplementation(kotlin("test"))
    testImplementation(libs.mockk)
}

tasks.test {
    useJUnitPlatform()
}
