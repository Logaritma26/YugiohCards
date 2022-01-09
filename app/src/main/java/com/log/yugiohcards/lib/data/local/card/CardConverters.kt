package com.log.yugiohcards.lib.data.local.card

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.log.yugiohcards.lib.data.remote.card.dto.CardImage
import com.log.yugiohcards.lib.data.remote.card.dto.CardPrice
import com.log.yugiohcards.lib.data.util.JsonParser

@ProvidedTypeConverter
class CardConverters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun imagesToString(images: List<CardImage>): String = jsonParser.toJson(
        images,
        object : TypeToken<ArrayList<CardImage>>() {}.type,
    ) ?: "[]"

    @TypeConverter
    fun stringToImages(json: String): List<CardImage> = jsonParser.fromJson(
        json,
        object : TypeToken<ArrayList<CardImage>>() {}.type,
    ) ?: emptyList()

    @TypeConverter
    fun priceToString(details: List<CardPrice>): String = jsonParser.toJson(
        details,
        object : TypeToken<ArrayList<CardPrice>>() {}.type,
    ) ?: "[]"

    @TypeConverter
    fun stringToPrice(json: String): List<CardPrice> = jsonParser.fromJson(
        json,
        object : TypeToken<ArrayList<CardPrice>>() {}.type,
    ) ?: emptyList()

}