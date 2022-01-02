package com.log.yugiohcards.lib.presentation.util

import com.log.yugiohcards.lib.presentation.util.Constants.cardType
import com.log.yugiohcards.lib.presentation.util.card_enums.Type


//fun String.isCardTypeOf(type: Type): Boolean = this.lowercase() == type.toString().lowercase()

fun String.isCardTypeOf(type: Type): Boolean = cardType[this.lowercase()] == type

fun String.typeSuffixed(): String = this.removeSuffix(" Monster")
