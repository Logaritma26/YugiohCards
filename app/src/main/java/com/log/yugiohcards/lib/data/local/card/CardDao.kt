package com.log.yugiohcards.lib.data.local.card

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCards(cards: List<CardEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardEntity)

    @Query("DELETE FROM CardEntity WHERE id IN(:ids)")
    suspend fun deleteCards(ids: List<Int>)

    @Query("DELETE FROM CardEntity WHERE id = :id")
    suspend fun deleteCard(id: Int)

    @Query("SELECT * FROM CardEntity ORDER BY RANDOM () LIMIT 1 ")
    suspend fun getRandom(): CardEntity?

    @Query("SELECT * FROM CardEntity WHERE id = :id")
    fun getSelected(id: Int): Flow<CardEntity>

    @Query("SELECT * FROM CardEntity")
    suspend fun getAllCards(): List<CardEntity>

    @Query("UPDATE CardEntity SET favorite = :favorite WHERE id = :id")
    suspend fun updateFavorite(id: Int, favorite: Boolean)

    @Query("SELECT * FROM CardEntity WHERE favorite = 1")
    fun getFavorites(): Flow<List<CardEntity>>

}