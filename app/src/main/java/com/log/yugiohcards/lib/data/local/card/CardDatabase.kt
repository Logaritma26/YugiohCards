package com.log.yugiohcards.lib.data.local.card

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [CardEntity::class],
    version = 1,
)
@TypeConverters(CardConverters::class)
abstract class CardDatabase : RoomDatabase() {

    abstract val dao: CardDao

}