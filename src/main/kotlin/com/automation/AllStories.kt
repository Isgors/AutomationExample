package com.automation

import com.automation.api.steps.UserSteps
import org.jbehave.core.configuration.Configuration
import org.jbehave.core.configuration.MostUsefulConfiguration
import org.jbehave.core.embedder.Embedder
import org.jbehave.core.embedder.StoryControls
import org.jbehave.core.embedder.UnmodifiableEmbedderControls
import org.jbehave.core.io.CodeLocations.codeLocationFromClass
import org.jbehave.core.io.LoadFromClasspath
import org.jbehave.core.io.StoryFinder
import org.jbehave.core.junit.JUnitStories
import org.jbehave.core.reporters.Format.CONSOLE
import org.jbehave.core.reporters.StoryReporterBuilder
import org.jbehave.core.steps.*


class AllStories : JUnitStories() {

    override fun configuration(): Configuration {
        return MostUsefulConfiguration()
            .useStoryLoader(LoadFromClasspath(this.javaClass))
            .useStoryReporterBuilder(getStoryReporterBuilder())
            .useStoryControls(StoryControls()
                .doResetStateBeforeScenario(true)
                .doIgnoreMetaFiltersIfGivenStory(true)
            )
            .useParameterControls(ParameterControls()
                .useDelimiterNamedParameters(true)
            )
            .useParameterConverters(getConverters())
    }

    override fun stepsFactory(): InjectableStepsFactory {
        val stepFileList: MutableList<Steps> = mutableListOf()
        stepFileList.add(UserSteps())

        return InstanceStepsFactory(configuration(), stepFileList)
    }

    override fun storyPaths(): MutableList<String> {
        return StoryFinder().findPaths(
            codeLocationFromClass(
                this.javaClass
            ),
            listOf("**/*.story"),
            listOf("")
        )
    }

    override fun configuredEmbedder(): Embedder? {
        val embedder = super.configuredEmbedder()
        return if (embedder.embedderControls() !is UnmodifiableEmbedderControls) {
            configureEmbedderControls(embedder)
        } else embedder
    }

    private fun getStoryReporterBuilder(): StoryReporterBuilder? {
        return StoryReporterBuilder()
            .withDefaultFormats()
            .withFailureTrace(true)
            .withFailureTraceCompression(true)
            .withCodeLocation(codeLocationFromClass(this.javaClass))
            .withFormats(CONSOLE)
            .withRelativeDirectory("jbehave-report")
    }

    private fun getConverters(): ParameterConverters {
        return ParameterConverters()
    }

    private fun configureEmbedderControls(embedder: Embedder): Embedder {
        embedder.embedderControls()
            .doIgnoreFailureInStories(true)
            .useStoryTimeouts("7200")
            .doFailOnStoryTimeout(false)
            .doGenerateViewAfterStories(true)
            .doIgnoreFailureInView(false)
            .doVerboseFailures(true)
        return embedder
    }

}