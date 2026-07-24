import net.scratch221171.mdk.build.req

plugins {
    id("neoforge-common-conventions")
    id("neoforge-common-config-conventions")
    id("com.diffplug.spotless") version "8.7.0"
}

val modonomiconVersion: String by project

dependencies {
    compileOnly(libs.modonomicon.common, req(modonomiconVersion))
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
