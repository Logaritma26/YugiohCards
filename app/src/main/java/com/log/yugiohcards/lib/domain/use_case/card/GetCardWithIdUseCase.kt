package com.log.yugiohcards.lib.domain.use_case.card

import com.log.yugiohcards.lib.domain.model.card.Card
import com.log.yugiohcards.lib.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCardWithIdUseCase @Inject constructor(
    private val repository: CardRepository,
) {
    suspend operator fun invoke(cardId: Int): Flow<Card> = repository.getSelectedCard(cardId)
}