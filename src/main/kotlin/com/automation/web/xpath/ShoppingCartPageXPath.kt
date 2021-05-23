package com.automation.web.xpath

enum class ShoppingCartPageXPath (val xpath: String) {
    PRODUCT_NAME("//*[@id=\"sacola\"]/div[1]/div/table/tbody/tr[1]/td[2]/a"),
    PRODUCT_PRICE("//*[@id=\"sacola\"]/div[1]/div/table/tbody/tr[1]/td[3]"),
    PRODUCT_TOTAL_PRICE("//*[@id=\"sacola\"]/div[1]/div/table/tbody/tr[1]/td[5]")
}