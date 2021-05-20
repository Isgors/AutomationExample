package com.automation.api.steps

import com.automation.utils.logger
import org.jbehave.core.annotations.Given
import org.jbehave.core.steps.Steps
import org.springframework.stereotype.Component

@Component
class UserSteps : Steps() {

    val logger = logger()

    @Given("a user is created with \$name name")
    fun createUser(name: String) {
        logger.info("Name is $name")
    }


}