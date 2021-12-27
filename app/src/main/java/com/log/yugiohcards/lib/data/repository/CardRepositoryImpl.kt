package com.log.yugiohcards.lib.data.repository

import com.log.yugiohcards.core.util.Process
import com.log.yugiohcards.core.util.Resource
import com.log.yugiohcards.di.ApplicationScope
import com.log.yugiohcards.di.DefaultDispatcher
import com.log.yugiohcards.di.IoDispatcher
import com.log.yugiohcards.lib.data.local.card.CardDao
import com.log.yugiohcards.lib.data.remote.card.CardApi
import com.log.yugiohcards.lib.data.remote.card.dto.CardDto
import com.log.yugiohcards.lib.data.remote.card.dto.CardsDto
import com.log.yugiohcards.lib.data.remote.card.dto.toCardEntity
import com.log.yugiohcards.lib.domain.model.card.Card
import com.log.yugiohcards.lib.domain.repository.CardRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val api: CardApi,
    private val dao: CardDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : CardRepository {

    override suspend fun getAllCardsRemote(): Resource<CardsDto> =
        withContext(ioDispatcher) {
            try {
                val cards = api.getCards()
                Timber.d("cards size: ${cards.data.size}")
                Resource.Success(data = cards)
            } catch (e: Exception) {
                Timber.e(e.localizedMessage ?: e.toString())
                Resource.Error(message = e.localizedMessage ?: e.toString())
            }
        }

    override suspend fun saveCardsToLocal(cards: List<CardDto>): Process =
        withContext(ioDispatcher) {
            try {
                dao.deleteCards(cards.map { it.id })
                dao.insertCards(cards.map { it.toCardEntity() })
                Process.Success()
            } catch (e: Exception) {
                Process.Fail(message = e.localizedMessage ?: e.toString())
            }
        }


    override suspend fun saveCardToLocal(card: CardDto): Process =
        withContext(ioDispatcher) {
            try {
                dao.deleteCard(card.id)
                dao.insertCard(card.toCardEntity())
                Process.Success()
            } catch (e: Exception) {
                Process.Fail(message = e.localizedMessage ?: e.toString())
            }
        }

    override suspend fun getRandomCard(): Card =
        withContext(ioDispatcher) {
            dao.getRandom().toCard()
        }

    override suspend fun getSelectedCard(id: Int): Card =
        withContext(ioDispatcher) {
            dao.getSelected(id).toCard()
        }

    override suspend fun checkIsRoomEmpty(): Boolean =
        withContext(ioDispatcher) {
            dao.getAllCards().isEmpty()
        }

}