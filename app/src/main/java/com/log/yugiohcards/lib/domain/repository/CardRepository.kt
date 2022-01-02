package com.log.yugiohcards.lib.domain.repository

import com.log.yugiohcards.lib.data.util.Process
import com.log.yugiohcards.lib.data.util.Resource
import com.log.yugiohcards.lib.data.remote.card.dto.CardDto
import com.log.yugiohcards.lib.data.remote.card.dto.CardsDto
import com.log.yugiohcards.lib.domain.model.card.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun getAllCardsRemote(): Resource<CardsDto>

    suspend fun saveCardsToLocal(cards: List<CardDto>): Process

    suspend fun saveCardToLocal(card: CardDto): Process

    suspend fun getRandomCard(): Card

    suspend fun getSelectedCard(id: Int): Flow<Card>

    suspend fun checkIsRoomEmpty(): Boolean

    suspend fun updateFavoriteCard(id: Int, favorite: Boolean)

    suspend fun getFavoriteCards(): Flow<List<Card>>
}