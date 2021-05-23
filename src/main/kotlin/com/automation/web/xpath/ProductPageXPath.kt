package com.automation.web.xpath

enum class ProductPageXPath (val xpath: String) {
    PRODUCT_NAME("//*[@id=\"divProdutoVariacao\"]/div[1]/div[3]/h1"),
    PRODUCT_PRICE("//*[@id=\"divProdutoVariacao\"]/div[2]/div[3]/div[1]/div[1]/div/div"),
    PRODUCT_SUBSCRIBER_PRICE("//*[@id=\"divProdutoVariacao\"]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/span[1]"),
    BTN_ADD_TO_CART("//*[@id=\"adicionarAoCarrinho\"]")
}