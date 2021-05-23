package com.automation.web

import io.github.bonigarcia.wdm.DriverManagerType
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*


@Component
class BrowserDriverConfiguration {

    @Value("\${browserType}")
    private lateinit var browserType : String

    val driver: WebDriver
        get(){

            val inputStream = Thread.currentThread().contextClassLoader.getResourceAsStream("my.properties")
            val properties = Properties()
            properties.load(inputStream)
            browserType = properties.getProperty("browserType")

                return when(browserType){
                    "CHROME" -> configureChromeDriver()
                    else -> throw NotImplementedError("$browserType not implemented yet")
                }
            }

    private fun configureChromeDriver() : ChromeDriver {
        val driverManager = WebDriverManager.getInstance(DriverManagerType.CHROME)
        driverManager.version("90.0.4430.24")
        driverManager.setup()

        val options = ChromeOptions()
        options.addArguments("start-maximized")
        options.addArguments("enable-automation")
        options.addArguments("--no-sandbox")
        options.addArguments("--disable-infobars")
        options.addArguments("--disable-dev-shm-usage")
        options.addArguments("--disable-browser-side-navigation")
        options.addArguments("--disable-gpu")

        return ChromeDriver(options)
    }

}