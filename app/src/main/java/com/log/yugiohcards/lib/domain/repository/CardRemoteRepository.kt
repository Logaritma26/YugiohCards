package com.log.yugiohcards.lib.domain.repository

import com.log.yugiohcards.lib.data.remote.card.dto.CardDto
import com.log.yugiohcards.lib.data.remote.card.dto.CardsDto

interface CardRemoteRepository {

    suspend fun getAllCards(): CardsDto

    suspend fun getRandomCard(): CardDto

}