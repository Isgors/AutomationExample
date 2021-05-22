package com.automation.api.jbehave

import com.automation.utils.logger
import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeScenario
import org.jbehave.core.annotations.BeforeStories
import org.jbehave.core.annotations.ScenarioType

class TestLifeCycle {

    private val log = logger()

    @BeforeStories
    fun beforeStories() = log.info("Before stories..")

    @BeforeScenario (uponType = ScenarioType.NORMAL)
    fun beforeNormalScenario() = log.info("Before Normal Scenario..")

    @BeforeScenario (uponType = ScenarioType.EXAMPLE)
    fun beforeExampleScenario() = log.info("Before Example Scenario..")

    @AfterStories
    fun afterStories() = log.info("After Stories..")

}