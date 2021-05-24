package com.automation.jbehave

import com.automation.utils.logger
import org.apache.commons.lang3.StringUtils
import org.jbehave.core.failures.UUIDExceptionWrapper
import org.jbehave.core.model.*
import org.jbehave.core.reporters.NullStoryReporter


class LoggingStoryReporter : NullStoryReporter() {

    private val log = logger()

    private var runningStoryStatus = ThreadLocal<Boolean>()
    private var storyThreadLocal = ThreadLocal<Story>()

    override fun storyNotAllowed(story: Story?, filter: String?) {
        log.info("{} (NOT ALLOWED [filter: {}])", story, filter)
    }

    override fun storyCancelled(story: Story?, storyDuration: StoryDuration?) {
        log.info("{} (CANCELLED [duration: {}])", story, storyDuration)
    }

    override fun beforeStory(story: Story, givenStory: Boolean) {
        if (givenStory) {
            // Given Story is invoked when actual story already began.
            // Thus, if we set a thread local story to a given story,
            // It will overwrite the actual (invoking) story. So, just ignoring.
            return
        }
        storyThreadLocal.set(story)
        if (story.name != "BeforeStories" && story.name != "AfterStories") {
            runningStoryStatus.set(true)
            reportBeforeStory(story)
        }
    }

    protected fun reportBeforeStory(story: Story) {
        log.info("")
        log.info(StringUtils.repeat("#", 80))
        log.info("Begin Story: " + story.name)
        log.info(StringUtils.repeat("#", 80))
    }

    override fun afterStory(givenStory: Boolean) {
        if (givenStory) {
            return
        }
        val story = storyThreadLocal.get()
        if (story == null) {
            log.warn("Story has not been set!")
            return
        }
        if (story.name != null && story.name != "BeforeStories"
                && story.name != "AfterStories") {
            reportAfterStory(storyThreadLocal.get())
        }
    }

    private fun reportAfterStory(story: Story) {
        val status = if (runningStoryStatus.get()) " PASSED " else " FAILED "
        log.info(StringUtils.repeat("#", 80))
        log.info("End Story: {}", story.name)
        log.info("Status: {}", status)
        log.info(StringUtils.repeat("#", 80))
    }

    override fun narrative(narrative: Narrative) {
        log.info(narrative.toString())
    }

    override fun lifecyle(lifecycle: Lifecycle) {
        log.info(lifecycle.toString())
    }

    override fun scenarioNotAllowed(scenario: Scenario?, filter: String?) {
        log.info("{} (NOT ALLOWED [filter: {}])", scenario, filter)
    }

    override fun beforeScenario(scenarioTitle: String?) {
        log.info("")
        log.info(StringUtils.repeat("=", 80))
        log.info("Scenario: {}", scenarioTitle)
        log.info(StringUtils.repeat("=", 80))
    }

    override fun scenarioMeta(meta: Meta) {
        log.info(meta.toString())
    }

    override fun successful(step: String?) {
        log.info("{} (SUCCESSFUL)", step)
    }

    override fun ignorable(step: String?) {
        log.info("{} (IGNORED)", step)
    }

    override fun pending(step: String?) {
        log.info("{} (PENDING)", step)
    }

    override fun notPerformed(step: String?) {
        log.info("{} (NOT PERFORMED)", step)
    }

    override fun failed(step: String?, cause: Throwable) {
        runningStoryStatus.set(false)
        val exceptionClass: String?
        val exceptionMessage: String?
        if (cause is UUIDExceptionWrapper) {
            exceptionClass = cause.cause!!.javaClass.name
            exceptionMessage = cause.cause!!.message
        } else {
            exceptionClass = cause.javaClass.name
            exceptionMessage = cause.message
        }
        val message = String.format("%s (FAILED), cause was: %s: %s", step, exceptionClass, exceptionMessage)
        log.error(message, cause)
    }

    override fun example(tableRow: Map<String?, String?>) {
        log.info("")
        log.info(StringUtils.repeat("-", 80))
        log.info("Example: {}", tableRow.toString())
        log.info(StringUtils.repeat("-", 80))
    }
}