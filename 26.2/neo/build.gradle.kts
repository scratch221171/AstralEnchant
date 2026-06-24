import net.scratch221171.mdk.build.req

plugins {
    id("neoforge-mod-conventions")
    id("neoforge-config-conventions")
}

val sodiumVersion: String by project
val reesesSodiumOptionsVersion: String by project
val sodiumExtraVersion: String by project
val jadeVersion: String by project
val emiVersion: String by project
val kotlinForForgeVersion: String by project
val emixxVersion: String by project
val betterModlistVersion: String by project
val cubesWithoutBordersVersion: String by project

// Mod Dependencies
dependencies {
    runtimeOnly(libs.sodium, req(sodiumVersion))
    runtimeOnly(libs.reeses.sodium.options, req(reesesSodiumOptionsVersion))
    runtimeOnly(libs.sodium.extra, req(sodiumExtraVersion))
    runtimeOnly(libs.jade, req(jadeVersion))
    runtimeOnly(libs.emi, req(emiVersion))
    runtimeOnly(libs.kotlinForForge, req(kotlinForForgeVersion))
    runtimeOnly(libs.emixx, req(emixxVersion))
    runtimeOnly(libs.betterModlist, req(betterModlistVersion))
    runtimeOnly(libs.cubesWithoutBorders, req(cubesWithoutBordersVersion))
}
