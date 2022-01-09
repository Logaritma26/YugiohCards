package com.log.yugiohcards.lib.domain.use_case.card

import com.log.yugiohcards.di.DefaultDispatcher
import com.log.yugiohcards.lib.domain.model.card.Card
import com.log.yugiohcards.lib.domain.repository.CardRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllCardsUseCase @Inject constructor(
    private val repository: CardRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(): List<Card> = withContext(defaultDispatcher) {
        repository.getAllCards().map {
            it.toCard()
        }
    }
}