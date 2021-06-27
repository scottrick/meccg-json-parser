package com.hatfat.meccg.json.parse.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MECCGCard(
    @SerializedName("Set") val set: String?,
    @SerializedName("Primary") val primary: String?,
    @SerializedName("Alignment") val alignment: String?,
    @SerializedName("MEID") val id: String?,
    @SerializedName("Artist") val artist: String?,
    @SerializedName("Rarity") val rarity: String?,
    @SerializedName("Precise") val precise: String?,
    @SerializedName("NameEN") val nameEN: String?,
    @SerializedName("NameDU") val nameDU: String?,
    @SerializedName("NameSP") val nameSP: String?,
    @SerializedName("NameFN") val nameFN: String?,
    @SerializedName("NameFR") val nameFR: String?,
    @SerializedName("NameGR") val nameGR: String?,
    @SerializedName("NameIT") val nameIT: String?,
    @SerializedName("NameJP") val nameJP: String?,
    @SerializedName("ImageName") val imageName: String?,
    @SerializedName("Text") val text: String?,
    @SerializedName("Skill") val skill: String?,
    @SerializedName("MPs") val mp: String?,
    @SerializedName("Mind") val mind: String?,
    @SerializedName("Direct") val direct: String?,
    @SerializedName("General") val general: String?,
    @SerializedName("Prowess") val prowess: String?,
    @SerializedName("Body") val body: String?,
    @SerializedName("Corruption") val corruption: String?,
    @SerializedName("Home") val home: String?,
    @SerializedName("Unique") val unique: String?,
    @SerializedName("Secondary") val secondary: String?,
    @SerializedName("Race") val race: String?,
    @SerializedName("RWMPs") val rwmps: String?,
    @SerializedName("Site") val site: String?,
    @SerializedName("Path") val path: String?,
    @SerializedName("Region") val region: String?,
    @SerializedName("RPath") val rpath: String?,
    @SerializedName("Playable") val playable: String?,
    @SerializedName("GoldRing") val goldRing: String?,
    @SerializedName("GreaterItem") val greaterItem: String?,
    @SerializedName("MajorItem") val majorItem: String?,
    @SerializedName("MinorItem") val minorItem: String?,
    @SerializedName("Information") val information: String?,
    @SerializedName("Palantiri") val palantiri: String?,
    @SerializedName("Scroll") val scroll: String?,
    @SerializedName("Hoard") val hoard: String?,
    @SerializedName("Gear") val gear: String?,
    @SerializedName("Non") val non: String?,
    @SerializedName("Haven") val haven: String?,
    @SerializedName("Stage") val stage: String?,
    @SerializedName("Strikes") val strikes: String?,
    @SerializedName("code") val code: String?,
    @SerializedName("Specific") val specific: String?,
    @SerializedName("fullCode") val fullCode: String?,
    @SerializedName("normalizedtitle") val normalizedTitle: String?,
    @SerializedName("DCpath") val dcPath: String?,
    @SerializedName("dreamcard") val dreamcard: Boolean?,
    @SerializedName("released") val released: Boolean?,
    @SerializedName("erratum") val erratum: Boolean?,
    @SerializedName("ice_errata") val iceErrata: Boolean?,
    @SerializedName("extras") val extra: Boolean?,
) : Serializable, Comparable<MECCGCard> {

    @delegate:Transient
    val sortableTitle: String by lazy {
        normalizedTitle ?: ""
    }

    @delegate:Transient
    val oldImageUrl: String by lazy {
        "https://raw.githubusercontent.com/vastorper/dc/master/graphics/Metw/${dcPath}"
    }

    @delegate:Transient
    val imageUrl: String by lazy {
        println("https://raw.githubusercontent.com/usmcgeek/mer/master/data/remastered_style/${set?.toLowerCase()}/${nameEN}.png")
        "https://raw.githubusercontent.com/usmcgeek/mer/master/data/remastered_style/${set?.toLowerCase()}/${nameEN}.png"
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