package com.log.yugiohcards.lib.domain.use_case.card

import com.log.yugiohcards.core.util.Process
import com.log.yugiohcards.core.util.Resource
import com.log.yugiohcards.di.ApplicationScope
import com.log.yugiohcards.lib.data.remote.card.dto.CardDto
import com.log.yugiohcards.lib.domain.repository.CardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SaveAllRemoteCardsUseCase @Inject constructor(
    private val repository: CardRepository,
    @ApplicationScope private val externalScope: CoroutineScope,
) {
    // TODO remove commented codes
    suspend operator fun invoke() {
        externalScope.launch {
            val isEmpty = repository.checkIsRoomEmpty()
            Timber.d("cache is empty: $isEmpty")
            if (isEmpty) {
                val res = repository.getAllCardsRemote()
                if (res is Resource.Success) {
                    res.data?.data?.let {
                        var failCount = 0
                        var successCount = 0
                        val subLists: List<List<CardDto>> = it.chunked(400)
                        for (chunk in subLists) {
                            val save = repository.saveCardsToLocal(chunk)
                            if (save is Process.Success) {
                                successCount++
                            } else {
                                failCount++
                            }
                            Timber.d("success count: $successCount, fail count: $failCount")
                        }
                        Timber.d("result -> success count: $successCount, fail count: $failCount")
                    }
                }
            } else {
                Timber.d("cache wasn't empty")
            }
        }

    }
}