package com.automation.web.pages

import com.automation.web.BrowserDriverConfiguration
import com.automation.web.xpath.CommonXPath
import org.apache.commons.lang3.RandomStringUtils
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.stereotype.Component
import ru.yandex.qatools.ashot.AShot
import java.io.File
import javax.imageio.ImageIO


@Component
class PetzSiteComponent {

    private lateinit var element: WebElement

    private lateinit var driver: WebDriver

    fun openBrowser(){
        driver = BrowserDriverConfiguration().driver
    }

    fun accessUrl(url: String) = driver[url]

    fun searchFor(value: String) {
        element = driver.findElement(By.xpath(CommonXPath.SEARCH_BAR.xpath))
        element.sendKeys(value)
    }

    fun clickElement(value: String) {
        element = driver.findElement(By.xpath(value))
        element.click()
    }

    fun pressKey(key: Keys) = element.sendKeys(key)

    fun getElementValue(value: String) : String  {
        element = driver.findElement(By.xpath(value))
        return element.text
    }

    fun waitUntilElementIsVisible(value: String){
        val wait = WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
    }

    fun takeScreenshot(imageName: String = RandomStringUtils.randomAlphanumeric(5)) {
        val screenshot = AShot().takeScreenshot(driver)
        ImageIO.write(screenshot.image, "png", File("target\\log\\$imageName.png"));
    }

}