import net.scratch221171.mdk.build.configureConfigSourceSet
import net.scratch221171.mdk.build.includeConfigOutput

plugins.withId("java-library") {
    val config = configureConfigSourceSet()
    includeConfigOutput(config)
}
