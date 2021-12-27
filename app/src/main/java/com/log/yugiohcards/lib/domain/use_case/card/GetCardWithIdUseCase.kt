package com.log.yugiohcards.lib.domain.use_case.card

import com.log.yugiohcards.core.util.Resource
import com.log.yugiohcards.lib.domain.model.card.Card
import com.log.yugiohcards.lib.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCardWithIdUseCase @Inject constructor(
    private val repository: CardRepository,
) {
    operator fun invoke(cardId: Int): Flow<Resource<Card>> = flow {
        emit(Resource.Loading())
        try {
            val card = repository.getSelectedCard(cardId)
            emit(Resource.Success(data = card))
        } catch (e: Exception) {
            emit(Resource.Error(message = (e.localizedMessage ?: e.toString())))
        }
    }
}