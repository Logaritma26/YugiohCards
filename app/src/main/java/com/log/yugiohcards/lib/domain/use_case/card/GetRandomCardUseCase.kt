package com.log.yugiohcards.lib.domain.use_case.card

import com.log.yugiohcards.lib.data.util.Resource
import com.log.yugiohcards.lib.domain.model.card.Card
import com.log.yugiohcards.lib.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRandomCardUseCase @Inject constructor(
    private val repository: CardRepository,
) {
    operator fun invoke(): Flow<Resource<Card>> = flow {
        emit(Resource.Loading())
        try {
            val card: Card = repository.getRandomCard()
            emit(Resource.Success(data = card))
        } catch (e: Exception) {
            emit(Resource.Error(message = (e.localizedMessage ?: e.toString())))
        }
    }
}