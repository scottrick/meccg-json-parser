package com.hatfat.meccg.json.parse.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MECCGCard(
    @SerializedName("Set") val set: String?,
    @SerializedName("Primary") val primary: String?,
    @SerializedName("Alignment") val alignment: String?,
    @SerializedName("MEID") val id: String?,
    @SerializedName("Artist") var artist: String?,
    @SerializedName("Rarity") var rarity: String?,
    @SerializedName("Precise") var precise: String?,
    @SerializedName("NameEN") var nameEN: String?,
    /* update filter functions if we enable these again */
//    @SerializedName("NameDU") var nameDU: String?,
//    @SerializedName("NameSP") var nameSP: String?,
//    @SerializedName("NameFN") var nameFN: String?,
//    @SerializedName("NameFR") var nameFR: String?,
//    @SerializedName("NameGR") var nameGR: String?,
//    @SerializedName("NameIT") var nameIT: String?,
//    @SerializedName("NameJP") var nameJP: String?,
    @SerializedName("ImageName") var imageName: String?,
    @SerializedName("Text") var text: String?,
    @SerializedName("Skill") var skill: String?,
    @SerializedName("MPs") var mp: String?,
    @SerializedName("Mind") var mind: String?,
    @SerializedName("Direct") var direct: String?,
    @SerializedName("General") var general: String?,
    @SerializedName("Prowess") var prowess: String?,
    @SerializedName("Body") var body: String?,
    @SerializedName("Corruption") var corruption: String?,
    @SerializedName("Home") var home: String?,
    @SerializedName("Unique") var unique: String?,
    @SerializedName("Secondary") var secondary: String?,
    @SerializedName("Race") var race: String?,
    @SerializedName("RWMPs") var rwmps: String?,
    @SerializedName("Site") var site: String?,
    @SerializedName("Path") var path: String?,
    @SerializedName("Region") var region: String?,
    @SerializedName("RPath") var rpath: String?,
    @SerializedName("Playable") var playable: String?,
    @SerializedName("GoldRing") var goldRing: String?,
    @SerializedName("GreaterItem") var greaterItem: String?,
    @SerializedName("MajorItem") var majorItem: String?,
    @SerializedName("MinorItem") var minorItem: String?,
    @SerializedName("Information") var information: String?,
    @SerializedName("Palantiri") var palantiri: String?,
    @SerializedName("Scroll") var scroll: String?,
    @SerializedName("Hoard") var hoard: String?,
    @SerializedName("Gear") var gear: String?,
    @SerializedName("Non") var non: String?,
    @SerializedName("Haven") var haven: String?,
    @SerializedName("Stage") var stage: String?,
    @SerializedName("Strikes") var strikes: String?,
    @SerializedName("code") var code: String?,
    @SerializedName("Specific") var specific: String?,
    @SerializedName("fullCode") var fullCode: String?,
    @SerializedName("normalizedtitle") var normalizedTitle: String?,
    @SerializedName("DCpath") var dcPath: String?,
    @SerializedName("dreamcard") var dreamcard: Boolean?,
    @SerializedName("released") var released: Boolean?,
    @SerializedName("erratum") var erratum: Boolean?,
    @SerializedName("ice_errata") var iceErrata: Boolean?,
    @SerializedName("extras") var extra: Boolean?,
) : Serializable, Comparable<MECCGCard> {

    @delegate:Transient
    val sortableTitle: String by lazy {
        normalizedTitle ?: ""
    }

    override fun compareTo(other: MECCGCard): Int {
        if (sortableTitle.isBlank() && other.sortableTitle.isBlank()) {
            return 0
        }

        if (sortableTitle.isBlank()) {
            return -1
        }

        if (other.sortableTitle.isBlank()) {
            return 1
        }

        return sortableTitle.compareTo(other.sortableTitle)
    }
}