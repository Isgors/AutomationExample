package com.automation.web.steps

import com.automation.utils.logger
import com.automation.web.model.Product
import com.automation.web.pages.PetzSiteComponent
import com.automation.web.xpath.ProductPageXPath
import com.automation.web.xpath.ShoppingCartPageXPath
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import org.jbehave.core.steps.Steps
import org.openqa.selenium.Keys
import org.springframework.stereotype.Component

@Component
class PetzSiteSteps (private val petzSiteComponent : PetzSiteComponent) : Steps() {

    private val HOME_PAGE_URL = "https://www.petz.com.br"

    private val log = logger()

    private val currentProduct = Product()

    @Given("the user opens the browser")
    fun openBrowser() {
        log.info("Accessing petz home page..")
        petzSiteComponent.openBrowser()
    }

    @Given("the user access the petz site home page")
    fun accessPetzHomePage() {
        log.info("Accessing petz home page..")
        petzSiteComponent.accessUrl(HOME_PAGE_URL)
        petzSiteComponent.takeScreenshot("1 - Accessing petz home page")
    }

    @Given("the user search for \$value")
    @Alias("the user search for <value>")
    fun searchFor(value: String) {
        petzSiteComponent.searchFor(value)
        Thread.sleep(1000)
        petzSiteComponent.takeScreenshot("2 - User search for $value")
    }

    @When("the user select the \$value element")
    fun selectValue(value: Int) {
        for (i in 1..value) {
            petzSiteComponent.pressKey(Keys.DOWN)
        }
        petzSiteComponent.pressKey(Keys.ENTER)
        petzSiteComponent.takeScreenshot("3 - User select $value")
    }

    @When("save product info")
    fun saveProductInfo() {
        currentProduct.name = petzSiteComponent.getElementValue(ProductPageXPath.PRODUCT_NAME.xpath)
        currentProduct.price = petzSiteComponent.getElementValue(ProductPageXPath.PRODUCT_PRICE.xpath)
        currentProduct.subscriberPrice = petzSiteComponent.getElementValue(ProductPageXPath.PRODUCT_SUBSCRIBER_PRICE.xpath)
        petzSiteComponent.takeScreenshot("4 - Product info saved")

    }

    @When("the user add the product to the shopping cart")
    fun addProductToShoppingCart() {
        petzSiteComponent.clickElement(ProductPageXPath.BTN_ADD_TO_CART.xpath)
        petzSiteComponent.takeScreenshot("5 - User add the product to the shopping cart")
    }

    @Then("the product info remain the same")
    fun validateProductInfo() {
        petzSiteComponent.waitUntilElementIsVisible(ShoppingCartPageXPath.PRODUCT_NAME.xpath)

        petzSiteComponent.takeScreenshot("6 - Product info shopping cart validation")

        val product = Product()
        product.name = petzSiteComponent.getElementValue(ShoppingCartPageXPath.PRODUCT_NAME.xpath)
        product.price = petzSiteComponent.getElementValue(ShoppingCartPageXPath.PRODUCT_PRICE.xpath)
        product.subscriberPrice = currentProduct.subscriberPrice

        if(currentProduct != product){
            petzSiteComponent.endSession()

            throw IllegalStateException("Product info does not match | current product: $currentProduct | actual product: $product")
        }

    }

    @Then("product total price is correct")
    fun validateProductTotalPrice() {

        val totalPrice = petzSiteComponent.getElementValue(ShoppingCartPageXPath.PRODUCT_TOTAL_PRICE.xpath)

        if(totalPrice != currentProduct.price){
            petzSiteComponent.endSession()

            throw IllegalStateException("Expected total price is ${currentProduct.price} but actual total price is $totalPrice")
        }

    }

    @Then("the user ends the current session")
    fun closeBrowser() = petzSiteComponent.endSession()


}