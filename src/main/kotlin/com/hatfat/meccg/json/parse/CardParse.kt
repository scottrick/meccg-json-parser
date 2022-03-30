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

        var cardList: List<MECCGCard>
        val meccgCardListType: Type = object : TypeToken<List<MECCGCard>>() {}.type
        val cardsInputStream = FileInputStream(File("input/cards-dc.json"))
        val cardsReader = BufferedReader(InputStreamReader(cardsInputStream))
        cardList = gson.fromJson(cardsReader, meccgCardListType)

        println("Found ${cardList.size} cards.")

        cardList = removeUnreleasedCards(cardList)
        println("Removed unreleased cards, now have ${cardList.size} cards.")

        cardList = removeWizardsUnlimited(cardList)
        println("Removed The Wizards Unlimited, now have ${cardList.size} cards.")

        cardList = removeDreamcards(cardList)

        removeEmptyStrings(cardList)

        validateCardIds(cardList)
        validateCardDCPaths(cardList)

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

    private fun removeDreamcards(cardList: List<MECCGCard>): List<MECCGCard> {
        return cardList.filter { it.dreamcard == false }
    }

    /* also validate a few items exist that are necessary */
    private fun removeEmptyStrings(cardList: List<MECCGCard>) {
        for (card in cardList) {
            if (card.set.isNullOrEmpty()) println(" --> NULL set ${card.normalizedTitle}")
            if (card.primary.isNullOrEmpty()) println(" --> NULL primary ${card.normalizedTitle}")
            if (card.alignment.isNullOrEmpty()) println(" --> NULL alignment ${card.normalizedTitle}")
            if (card.id.isNullOrEmpty()) println(" --> NULL meid ${card.normalizedTitle}")
            if (card.artist.isNullOrEmpty()) card.artist = null
            if (card.rarity.isNullOrEmpty()) card.rarity = null
            if (card.precise.isNullOrEmpty()) card.precise = null
            if (card.nameEN.isNullOrEmpty()) card.nameEN = null
//            if (card.nameDU.isNullOrEmpty()) card.nameDU = null
//            if (card.nameSP.isNullOrEmpty()) card.nameSP = null
//            if (card.nameFN.isNullOrEmpty()) card.nameFN = null
//            if (card.nameFR.isNullOrEmpty()) card.nameFR = null
//            if (card.nameGR.isNullOrEmpty()) card.nameGR = null
//            if (card.nameIT.isNullOrEmpty()) card.nameIT = null
//            if (card.nameJP.isNullOrEmpty()) card.nameJP = null
            if (card.imageName.isNullOrEmpty()) card.imageName = null
            if (card.text.isNullOrEmpty()) card.text = null
            if (card.skill.isNullOrEmpty()) card.skill = null
            if (card.mp.isNullOrEmpty()) card.mp = null
            if (card.mind.isNullOrEmpty()) card.mind = null
            if (card.direct.isNullOrEmpty()) card.direct = null
            if (card.general.isNullOrEmpty()) card.general = null
            if (card.prowess.isNullOrEmpty()) card.prowess = null
            if (card.body.isNullOrEmpty()) card.body = null
            if (card.corruption.isNullOrEmpty()) card.corruption = null
            if (card.home.isNullOrEmpty()) card.home = null
            if (card.unique.isNullOrEmpty()) card.unique = null
            if (card.secondary.isNullOrEmpty()) card.secondary = null
            if (card.race.isNullOrEmpty()) card.race = null
            if (card.rwmps.isNullOrEmpty()) card.rwmps = null
            if (card.site.isNullOrEmpty()) card.site = null
            if (card.path.isNullOrEmpty()) card.path = null
            if (card.region.isNullOrEmpty()) card.region = null
            if (card.rpath.isNullOrEmpty()) card.rpath = null
            if (card.playable.isNullOrEmpty()) card.playable = null
            if (card.goldRing.isNullOrEmpty()) card.goldRing = null
            if (card.greaterItem.isNullOrEmpty()) card.greaterItem = null
            if (card.majorItem.isNullOrEmpty()) card.majorItem = null
            if (card.minorItem.isNullOrEmpty()) card.minorItem = null
            if (card.information.isNullOrEmpty()) card.information = null
            if (card.palantiri.isNullOrEmpty()) card.palantiri = null
            if (card.scroll.isNullOrEmpty()) card.scroll = null
            if (card.hoard.isNullOrEmpty()) card.hoard = null
            if (card.gear.isNullOrEmpty()) card.gear = null
            if (card.non.isNullOrEmpty()) card.non = null
            if (card.haven.isNullOrEmpty()) card.haven = null
            if (card.stage.isNullOrEmpty()) card.stage = null
            if (card.strikes.isNullOrEmpty()) card.strikes = null
            if (card.code.isNullOrEmpty()) card.code = null
            if (card.specific.isNullOrEmpty()) card.specific = null
            if (card.fullCode.isNullOrEmpty()) card.fullCode = null
            if (card.normalizedTitle.isNullOrEmpty()) println(" --> NULL normalizedTitle ${card.normalizedTitle}")
            if (card.dcPath.isNullOrEmpty()) println(" --> NULL dcPath ${card.normalizedTitle}")
        }
    }

    private fun validateCardIds(cardList: List<MECCGCard>) {
        println("Validating card IDs.");

        /* create set of all card ids */
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
            } else {
                cardIdSet.add(card.id)
            }
        }

        println("Found ${cardsThatNeedNewIds.size} cards that need an ID.")
    }

    private fun validateCardDCPaths(cardList: List<MECCGCard>) {
        println("Validating card DC paths.");

        for (card in cardList) {
            card.dcPath?.let {
                if (it.endsWith("DC.jpg")) {
                    println("Found card ending with DC.jpg: [${card.dcPath}] - Fixing.")

                    /* chop off the last 6 digits and re-append .jpg */
                    val newPrefix = it.subSequence(0, it.length - 6)
                    val newPath = "$newPrefix.jpg"
                    card.dcPath = newPath
                }
            }
        }
    }
}