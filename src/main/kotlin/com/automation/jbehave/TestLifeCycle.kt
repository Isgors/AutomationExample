package com.automation.jbehave

import com.automation.utils.FileService
import com.automation.utils.logger
import org.apache.commons.io.FileUtils
import org.jbehave.core.annotations.*
import org.springframework.stereotype.Component
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class TestLifeCycle {

    private val log = logger()

    @BeforeStories
    fun beforeStories() {
        log.info("Before stories..")
        FileService().createDirectory("target", "log")
    }

    @BeforeScenario (uponType = ScenarioType.NORMAL)
    fun beforeNormalScenario() = log.info("Before Normal Scenario..")

    @BeforeScenario (uponType = ScenarioType.EXAMPLE)
    fun beforeExampleScenario() = log.info("Before Example Scenario..")

    @AfterStories
    fun afterStories() = log.info("After Stories..")

    @AfterScenario (uponType = ScenarioType.NORMAL)
    fun afterNormalScenario() {

        log.info("After Normal Scenario..")

        createLogFolder()

    }

    @AfterScenario (uponType = ScenarioType.EXAMPLE)
    fun afterExampleScenario() {

        log.info("After Example Scenario..")

        createLogFolder()

    }

    private fun createLogFolder(){

        val currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss"))

        val destFile = FileService().createDirectory(".log", currentDateTime)
        val srcFile = File("target/log")

        FileUtils.moveDirectoryToDirectory(srcFile,destFile,true)
    }
}