package com.log.yugiohcards.lib.data.repository

import com.log.yugiohcards.core.util.Process
import com.log.yugiohcards.core.util.Resource
import com.log.yugiohcards.lib.data.local.card.CardDao
import com.log.yugiohcards.lib.data.remote.card.CardApi
import com.log.yugiohcards.lib.data.remote.card.dto.CardDto
import com.log.yugiohcards.lib.data.remote.card.dto.CardsDto
import com.log.yugiohcards.lib.data.remote.card.dto.toCardEntity
import com.log.yugiohcards.lib.domain.model.card.Card
import com.log.yugiohcards.lib.domain.repository.CardRepository
import timber.log.Timber

class CardRepositoryImpl(
    private val api: CardApi,
    private val dao: CardDao,
) : CardRepository {

    override suspend fun getAllCardsRemote(): Resource<CardsDto> {
        return try {
            val cards = api.getCards()
            Timber.d("cards size: ${cards.data.size}")
            Resource.Success(data = cards)
        } catch (e: Exception) {
            Timber.e(e.localizedMessage ?: e.toString())
            Resource.Error(message = e.localizedMessage ?: e.toString())
        }
    }

    override suspend fun saveCardsToLocal(cards: List<CardDto>): Process {
        try {
            dao.deleteCards(cards.map { it.id })
            dao.insertCards(cards.map { it.toCardEntity() })
        } catch (e: Exception) {
            Process.Fail(message = e.localizedMessage ?: e.toString())
        }
        return Process.Success()
    }

    override suspend fun saveCardToLocal(card: CardDto): Process {
        try {
            dao.deleteCard(card.id)
            dao.insertCard(card.toCardEntity())
        } catch (e: Exception) {
            Process.Fail(message = e.localizedMessage ?: e.toString())
        }
        return Process.Success()
    }

    override suspend fun getRandomCard(): Card = dao.getRandom().toCard()

    override suspend fun getSelectedCard(id: Int): Card = dao.getSelected(id).toCard()

    override suspend fun checkIsRoomEmpty(): Boolean = dao.getAllCards().isEmpty()

}