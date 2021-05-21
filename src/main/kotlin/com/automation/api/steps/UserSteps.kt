package com.automation.api.steps

import com.automation.api.service.PetStoreService
import com.automation.utils.logger
import io.swagger.petstore.models.User
import org.jbehave.core.annotations.Given
import org.jbehave.core.steps.Steps
import org.springframework.stereotype.Component

@Component
class UserSteps(private val petStoreService: PetStoreService) : Steps() {

    private val log = logger()

    private var userId: Long = 300

    @Given("a user is created with \$name name")
    fun createUser(name: String) {
        userId = userId.inc()

        val user = User()
        user.username = name
        user.id = userId

        log.info("User name is $name")
        log.info("User id is ${user.id}")

        petStoreService.user.createUser(user)
    }

}