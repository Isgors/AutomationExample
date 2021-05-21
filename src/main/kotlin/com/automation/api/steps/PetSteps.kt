package com.automation.api.steps

import com.automation.utils.logger
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import org.jbehave.core.steps.Steps
import org.springframework.stereotype.Component

@Component
class PetSteps : Steps() {

    val logger = logger()

    @Given("a pet is created with \$petName name")
    fun createPet(petName: String) {
        logger.info("Pet name is $petName")
    }

    @When("the pet \$petName is sold to the \$userName user")
    fun sellPet(petName: String, userName: String) {
        logger.info("Pet name is $petName and user name is $userName")
    }

    @When("the pet \$petName sales order status is changed to \$salesOrderStatus")
    fun updatePetSalesOrderStatus(petName: String, salesOrderStatus: String) {
        logger.info("The pet $petName sales order status is $salesOrderStatus")
    }

    @Then("the sales order status is \$salesOrderStatus")
    fun checkPetSalesOrderStatus(salesOrderStatus: String) {
        logger.info("Check if pet sales order status is $salesOrderStatus")
    }


}