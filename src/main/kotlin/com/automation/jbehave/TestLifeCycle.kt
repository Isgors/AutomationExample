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
    fun beforeStories() = log.info("Before stories..")


    @BeforeScenario (uponType = ScenarioType.NORMAL)
    fun beforeNormalScenario() {
        log.info("Before Normal Scenario..")
        FileService().createDirectory("target", "log")
    }

    @AfterStories
    fun afterStories() = log.info("After Stories..")

    @BeforeScenario (uponType = ScenarioType.EXAMPLE)
    fun beforeExampleScenario() {
        log.info("Before Example Scenario..")
        FileService().createDirectory("target", "log")
    }

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