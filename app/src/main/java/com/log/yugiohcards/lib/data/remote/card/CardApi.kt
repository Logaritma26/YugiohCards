package com.log.yugiohcards.lib.data.remote.card

import com.log.yugiohcards.lib.data.remote.card.dto.CardsDto
import retrofit2.http.GET

interface CardApi {

    @GET("/v7/cardinfo.php")
    suspend fun getCoins(): List<CardsDto>
}