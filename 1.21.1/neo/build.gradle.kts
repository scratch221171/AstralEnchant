plugins {
    id("neoforge-mod-conventions")
    id("neoforge-config-conventions")
    id("com.diffplug.spotless") version "8.7.0"
}

// Mod Dependencies
dependencies {
}

spotless {
    java {
        target("src/main/**/*.java")
        palantirJavaFormat()

        formatAnnotations()
        importOrder()
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
    }
}
