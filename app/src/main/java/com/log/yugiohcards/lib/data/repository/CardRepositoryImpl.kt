package com.log.yugiohcards.lib.data.repository

import com.log.yugiohcards.lib.data.util.Process
import com.log.yugiohcards.lib.data.util.Resource
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val api: CardApi,
    private val dao: CardDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
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

    // TODO delete local at the end
    override suspend fun saveCardsToLocal(cards: List<CardDto>): Process =
        withContext(ioDispatcher) {
            //var localLast: CardDto
            try {
                dao.deleteCards(cards.map { it.id })
                dao.insertCards(cards.map {
                    //localLast = it
                    it.toCardEntity()
                })
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
            dao.getRandom()!!.toCard()
        }

    override suspend fun getSelectedCard(id: Int): Flow<Card> = dao.getSelected(id)
        .flowOn(ioDispatcher)
        .map {
            it.toCard()
        }
        .flowOn(defaultDispatcher)


    override suspend fun checkIsRoomEmpty(): Boolean =
        withContext(ioDispatcher) {
            dao.getRandom() == null
        }

    override suspend fun updateFavoriteCard(id: Int, favorite: Boolean) =
        withContext(ioDispatcher) {
            dao.updateFavorite(id = id, favorite = favorite)
        }

    override suspend fun getFavoriteCards(): Flow<List<Card>> =
        dao.getFavorites()
            .flowOn(ioDispatcher)
            .map { list ->
                list.map { entity ->
                    entity.toCard()
                }
            }
            .flowOn(defaultDispatcher)


}