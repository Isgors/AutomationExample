package com.automation.api.steps

import com.automation.api.service.PetStoreService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.CALLS_REAL_METHODS
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserStepsTest {

    private val petStoreService = mock(PetStoreService::class.java, CALLS_REAL_METHODS)

    private val userSteps = UserSteps(petStoreService)


    @Test
    fun createUser_validName_userIsCreated() {
        userSteps.createUser("TEST")

        Assert.assertEquals("TEST", userSteps.currentUser.username)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createUser_invalidName_userIsCreated() = userSteps.createUser("")

}