package com.automation.api.steps

import com.automation.api.service.PetStoreService
import io.swagger.petstore.models.Status1Enum
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PetStepsTest {

    private val petStoreService = Mockito.mock(PetStoreService::class.java, Mockito.CALLS_REAL_METHODS)

    private val petSteps = PetSteps(petStoreService)


    @Test
    fun createPet_validName_petIsCreated() {
        petSteps.createPet("CATEGORY_TEST", "TEST")

        Assert.assertEquals("CATEGORY_TEST", petSteps.currentPet.category.name)
        Assert.assertEquals("TEST", petSteps.currentPet.name)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createPet_invalidName_exceptionIsThrown() = petSteps.createPet("", "TEST")

    @Test(expected = IllegalArgumentException::class)
    fun createPet_invalidCategory_exceptionIsThrown() = petSteps.createPet("CATEGORY_TEST", "")

    @Test
    fun getCategory_validCategoryName_categoryIsCreated() {
        val category = petSteps.getCategory("CATEGORY_TEST")

        Assert.assertEquals("CATEGORY_TEST", category.name)
        Assert.assertEquals(1, category.id)
    }

    @Test
    fun sellPet_validPet_petIsSold() {
        petSteps.createPet("CATEGORY_TEST", "TEST")

        petSteps.sellPet("TEST")

        Assert.assertEquals(1, petSteps.currentOrder.quantity)
        Assert.assertEquals(petSteps.currentPet.id, petSteps.currentOrder.petId)
    }

    @Test
    fun updatePetSalesOrderStatus_validStatus_salesOrderStatusIsUpdated() {
        petSteps.createPet("CATEGORY_TEST", "TEST")

        petSteps.sellPet("TEST")

        petSteps.updatePetSalesOrderStatus(Status1Enum.DELIVERED)

        Assert.assertEquals(Status1Enum.DELIVERED, petSteps.currentOrder.status)
    }
}