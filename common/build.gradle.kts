plugins {
    `java-library`
    id("common-config-conventions")
    id("com.diffplug.spotless") version "8.7.0"
}

repositories {
    mavenCentral()
}

dependencies {
    // Match the oldest supported loader classpath: 1.18.2 resolves slf4j-api to 1.8.0-beta4.
    compileOnly(libs.slf4j.api)
}

java.toolchain {
    languageVersion = JavaLanguageVersion.of(17)
}

spotless {
    java {
        target("src/main/**/*.java", "src/config/java/net/scratch221171/astralenchant/config/**/*.java")
        palantirJavaFormat()

        formatAnnotations()
        importOrder()
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
    }
}
