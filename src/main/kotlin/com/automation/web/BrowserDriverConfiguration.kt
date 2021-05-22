package com.automation.web

import io.github.bonigarcia.wdm.DriverManagerType
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component


@Component
class BrowserDriverConfiguration {

    @Value("\${browser:CHROME}")
    private lateinit var browserType : DriverManagerType

    val driver: WebDriver
        get(){
                return when(browserType){
                    DriverManagerType.CHROME -> configureChromeDriver()
                    else -> throw NotImplementedError("$browserType not implemented yet")
                }
            }

//    init {
//        driver = configureChromeDriver()
//        driver = when(browserType){
//            DriverManagerType.CHROME -> configureChromeDriver()
//            else -> throw NotImplementedError("$browserType not implemented yet")
//        }
//    }

    //val CHROME_BROWSER_VERSION = "90.0.4430.212"

    private fun configureChromeDriver() : ChromeDriver {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        val options = ChromeOptions()
        options.addArguments("start-maximized")
        options.addArguments("enable-automation")
        options.addArguments("--no-sandbox")
        options.addArguments("--disable-infobars")
        options.addArguments("--disable-dev-shm-usage")
        options.addArguments("--disable-browser-side-navigation")
        options.addArguments("--disable-gpu")
        return ChromeDriver()
    }

}