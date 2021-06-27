package com.hatfat.meccg.json.parse

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hatfat.meccg.json.parse.data.MECCGCard
import java.io.*
import java.lang.reflect.Type

class CardParse(
    private val gson: Gson
) {
    fun meccgJsonWork() {
        println("Parsing MECCG cards-dc json...")

        var cardList = emptyList<MECCGCard>()
        val meccgCardListType: Type = object : TypeToken<List<MECCGCard>>() {}.type
        val cardsInputStream = FileInputStream(File("input/cards-dc.json"))
        val cardsReader = BufferedReader(InputStreamReader(cardsInputStream))
        cardList = gson.fromJson(cardsReader, meccgCardListType)

        println("Found ${cardList.size} cards.")

        cardList = removeUnreleasedCards(cardList)
        println("Removed unreleased cards, now have ${cardList.size} cards.")

        cardList = removeWizardsUnlimited(cardList)
        println("Removed The Wizards Unlimited, now have ${cardList.size} cards.")

        validateCardIds(cardList)

        writeCardList(cardList, "output/cards-dc.json")
        println("Wrote ${cardList.size} cards.")
    }

    private fun writeCardList(cardList: List<MECCGCard>, filename: String) {
        val outputFile = File(filename)
        outputFile.parentFile.mkdirs()
        outputFile.createNewFile()
        val outputStream = FileOutputStream(outputFile)
        val writer = BufferedWriter(OutputStreamWriter(outputStream))
        gson.toJson(cardList, writer)
        writer.close()
        outputStream.close()
    }

    private fun removeUnreleasedCards(cardList: List<MECCGCard>): List<MECCGCard> {
        return cardList.filter { it.released == true }
    }

    private fun removeWizardsUnlimited(cardList: List<MECCGCard>): List<MECCGCard> {
        return cardList.filter { it.set?.equals("MEUL") == false }
    }

    private fun validateCardIds(cardList: List<MECCGCard>) {
        println("Validating card IDs.");

        /* create set of all card ids */
        var maxIdFound = 0;
        val cardIdSet = mutableSetOf<String>()
        val cardsThatNeedNewIds = mutableListOf<MECCGCard>()

        for (card in cardList) {
            if (card.id.isNullOrEmpty()
                || card.id == "TESTING"
                || card.id.toLowerCase() == "moved set"
                || card.id.toLowerCase() == "june 21"
                || card.id.toLowerCase() == "update may 2021"
                || card.id.toLowerCase() == "renamed"
                || card.id.toLowerCase() == "update june 21"
                || card.id.toLowerCase() == "testing jan 21"
                || card.id.toLowerCase() == "set change"
                || card.id.toLowerCase() == "type change"
                || card.id.toLowerCase() == " "
                || card.id.toLowerCase() == "df189"
            ) {
                cardsThatNeedNewIds.add(card)
            } else if (cardIdSet.contains(card.id)) {
                println(" --> ${card.normalizedTitle} ${card.id}")
            } else {
                cardIdSet.add(card.id)

//                card.id.toInt().let {
//                    if (it > maxIdFound) {
//                        maxIdFound = it
//                    }
//                }
            }
        }

        println("Found ${cardsThatNeedNewIds.size} cards that need an ID.")
    }
}