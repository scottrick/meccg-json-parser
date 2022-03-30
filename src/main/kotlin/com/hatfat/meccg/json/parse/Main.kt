package com.hatfat.meccg.json.parse

import com.google.gson.GsonBuilder

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    val gson = GsonBuilder()
//        .setPrettyPrinting()
        .disableHtmlEscaping()
        .create()

    val cardParse = CardParse(gson)
    cardParse.meccgJsonWork()
}