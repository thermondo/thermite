import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm

plugins {
    kotlin("jvm")
    alias(libs.plugins.dokka)
    alias(libs.plugins.vanniktech)
}

group = "de.thermondo"
version = "local"

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

mavenPublishing {
    publishToMavenCentral()
    // Only sign if we are publishing to Maven Central
    if (!gradle.startParameter.taskNames.any { it.contains("publishToMavenLocal") }) {
        signAllPublications()
    }

    coordinates(
        "de.thermondo",
        "thermite",
        System.getenv("VERSION") ?: "local"
    )

    configure(
        KotlinJvm(
            javadocJar = JavadocJar.Dokka("dokkaGenerateHtml"),
            sourcesJar = true,
        )
    )

    pom {
        name.set("Thermite")
        description.set("Thermite is an automation testing library for mobile applications.")
        url.set("https://github.com/thermondo/thermite")
        licenses {
            license {
                name.set("MIT License")
                url.set("https://github.com/thermondo/thermite/blob/main/LICENSE")
            }
        }
        developers {
            developer {
                id.set("thermondo")
                name.set("Thermondo GmbH")
                email.set("open-source@thermondo.de")
                url.set("https://thermondo.de")
                organization.set("Thermondo GmbH")
                organizationUrl.set("https://thermondo.de")
            }
        }
        scm {
            connection.set("scm:git:https://github.com/thermondo/thermite.git")
            developerConnection.set("scm:git:https://github.com/thermondo/thermite.git")
            url.set("https://github.com/thermondo/thermite")
        }
    }
}
