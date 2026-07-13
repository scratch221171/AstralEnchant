import net.scratch221171.mdk.build.req

plugins {
    id("neoforge-mod-conventions")
    id("neoforge-config-conventions")
    id("com.diffplug.spotless") version "8.7.0"
}

val ae2Version: String by project
val guidemeVersion: String by project
val mekanismVersion: String by project
val createVersion: String by project
val oritechVersion: String by project
val athenaVersion: String by project
val geckolibVersion: String by project
val architecturyApiVersion: String by project

// Mod Dependencies
dependencies {
    implementation(libs.guideme, req(guidemeVersion))
    ciRuntimeMods(libs.guideme, req(guidemeVersion))
    implementation(libs.ae2, req(ae2Version))
    ciRuntimeMods(libs.ae2, req(ae2Version))

    implementation(libs.mekanism, req(mekanismVersion))
    ciRuntimeMods(libs.mekanism, req(mekanismVersion))

    implementation(libs.create, req(createVersion))
    ciRuntimeMods(libs.create, req(createVersion))

    implementation(libs.oritech, req(oritechVersion))
    ciRuntimeMods(libs.oritech, req(oritechVersion))

    implementation(libs.athena, req(athenaVersion))
    ciRuntimeMods(libs.athena, req(athenaVersion))

    implementation(libs.geckolib, req(geckolibVersion))
    ciRuntimeMods(libs.geckolib, req(geckolibVersion))

    implementation(libs.architectury.api, req(architecturyApiVersion))
    ciRuntimeMods(libs.architectury.api, req(architecturyApiVersion))
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
