package com.automation.api.steps

import com.automation.api.service.PetStoreService
import com.automation.utils.logger
import io.swagger.petstore.models.Category
import io.swagger.petstore.models.Order
import io.swagger.petstore.models.Pet
import io.swagger.petstore.models.Status1Enum
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import org.jbehave.core.steps.Steps
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

@Component
class PetSteps(private val petStoreService: PetStoreService) : Steps() {

    private val log = logger()

    private var orderId: Long = 0

    private var petId: Long = 300

    var currentPet = Pet()

    var currentOrder = Order()


    @Given("a \$petCategoryName named \$petName is created")
    fun createPet(petCategoryName: String, petName: String) {

        if(petCategoryName.isEmpty() || petName.isEmpty())
            throw IllegalArgumentException("Pet name/category should not be empty")

        petId = petId.inc()

        val pet = Pet()
        pet.id = petId
        pet.name = petName
        pet.category = getCategory(petCategoryName)

        log.info("Pet name is $petName")
        log.info("Pet id is ${pet.id}")

        petStoreService.pet.addPet(pet)

        currentPet = pet
    }

    fun getCategory(categoryName :String): Category {
        val category = Category()
        category.id = 1
        category.name = categoryName
        return category
    }

    @When("the pet is sold to the \$userName user")
    fun sellPet(userName: String) {
        //Petstore API sales order only has petId as a parameter so the username here its just for the semantics

        //Verify if there is a pet created
        if(currentPet.name.isNullOrEmpty())
            throw IllegalArgumentException("Create pet before selling it")

        orderId = orderId.inc()

        val order = Order()
        order.id = orderId
        order.petId = currentPet.id
        order.quantity = 1

        petStoreService.store.createPlaceOrder(order)

        currentOrder = order

        log.info("Pet ${currentPet.name} was sold to user $userName and order id is $orderId")

    }

    @When("the pet sales order status is changed to \$salesOrderStatus")
    fun updatePetSalesOrderStatus(salesOrderStatus: Status1Enum) {
        //There are no PUT/update method for orders so i delete the order and create again with the new status

        petStoreService.store.deleteOrder(currentOrder.id)

        currentOrder.status = salesOrderStatus

        petStoreService.store.createPlaceOrder(currentOrder)

        log.info("Pet ${currentPet.name} sales order status changed to ${salesOrderStatus.value()}")

    }

    @Then("the sales order status is \$salesOrderStatus")
    fun checkPetSalesOrderStatus(salesOrderStatus: Status1Enum) {
        log.info("Check if pet sales order status is $salesOrderStatus")

        val order = petStoreService.store.getOrderById(currentOrder.id)

        if(order.status.equals(salesOrderStatus))
            throw Exception("Actual order status is ${order.status}")
    }

}