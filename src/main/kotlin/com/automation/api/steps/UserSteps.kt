package com.automation.api.steps

import com.automation.api.service.PetStoreService
import com.automation.utils.logger
import io.swagger.petstore.models.User
import org.jbehave.core.annotations.Given
import org.jbehave.core.steps.Steps
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

@Component
class UserSteps(private val petStoreService: PetStoreService) : Steps() {

    private val log = logger()

    private var userId: Long = 300

    var currentUser = User()


    @Given("a user is created with \$name name")
    fun createUser(name: String) {

        if(name.isEmpty())
            throw IllegalArgumentException("User name should not be empty")

        userId = userId.inc()

        val user = User()
        user.username = name
        user.id = userId

        petStoreService.user.createUser(user)

        currentUser = user

        log.info("User created with id ${user.id} and name $name")
    }

}