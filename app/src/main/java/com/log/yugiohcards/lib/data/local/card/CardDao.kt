package com.log.yugiohcards.lib.data.local.card

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCards(cards: List<CardEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardEntity)

    @Query("DELETE FROM cardEntity WHERE id IN(:ids)")
    suspend fun deleteCards(ids: List<Int>)

    @Query("DELETE FROM cardEntity WHERE id = :id")
    suspend fun deleteCard(id: Int)

    @Query("SELECT * FROM cardEntity ORDER BY RANDOM () LIMIT 1 ")
    suspend fun getRandom(): CardEntity

    @Query("SELECT * FROM cardEntity WHERE id = :id")
    suspend fun getSelected(id: Int): CardEntity

    @Query("SELECT * FROM cardEntity")
    suspend fun getAllCards(): List<CardEntity>

  /*  @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word || '%'")
    suspend fun getSingleCard(id: String): List<WordInfoEntity>*/
}