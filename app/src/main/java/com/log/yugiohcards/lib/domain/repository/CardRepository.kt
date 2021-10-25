package com.log.yugiohcards.lib.domain.repository

import com.log.yugiohcards.lib.domain.model.card.Card

interface CardRepository {

    suspend fun getCard(): Card

}