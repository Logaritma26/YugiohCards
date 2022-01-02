package com.log.yugiohcards.lib.domain.use_case.card

import com.log.yugiohcards.di.ApplicationScope
import com.log.yugiohcards.lib.domain.repository.CardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpdateFavoriteCardUseCase @Inject constructor(
    private val repository: CardRepository,
    @ApplicationScope private val externalScope: CoroutineScope,
) {
    operator fun invoke(id: Int, favorite: Boolean) {
        externalScope.launch {
            repository.updateFavoriteCard(
                id = id,
                favorite = favorite,
            )
        }
    }
}