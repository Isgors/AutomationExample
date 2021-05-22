package com.automation.web.steps

import com.automation.utils.logger
import com.automation.web.pages.PetzHomePage
import org.jbehave.core.annotations.Given
import org.jbehave.core.steps.Steps
import org.springframework.stereotype.Component

@Component
class PetzHomePageSteps (private val petzHomePage : PetzHomePage) : Steps() {

    private val log = logger()

    @Given("a client access the petz site home page")
    fun createUser() {
        log.info("Accessing petz home page..")
    }

}