package com.log.yugiohcards.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.log.yugiohcards.lib.data.local.card.CardConverters
import com.log.yugiohcards.lib.data.local.card.CardDatabase
import com.log.yugiohcards.lib.data.remote.card.CardApi
import com.log.yugiohcards.lib.data.repository.CardRepositoryImpl
import com.log.yugiohcards.lib.data.util.GsonParser
import com.log.yugiohcards.lib.domain.repository.CardRepository
import com.log.yugiohcards.lib.domain.use_case.card.GetCardWithIdUseCase
import com.log.yugiohcards.lib.domain.use_case.card.GetRandomCardUseCase
import com.log.yugiohcards.lib.domain.use_case.card.SaveAllRemoteCardsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CardModule {

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): CardDatabase {
        return Room.databaseBuilder(
            app,
            CardDatabase::class.java, "card_db",
        )
            .addTypeConverter(CardConverters(GsonParser(Gson())))
            .build()
    }


    @Provides
    @Singleton
    fun provideCardApi(): CardApi {
        return Retrofit.Builder()
            .baseUrl(CardApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CardApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: CardDatabase,
        api: CardApi
    ): CardRepository {
        return CardRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideGetRandomCardUseCase(repository: CardRepository): GetRandomCardUseCase =
        GetRandomCardUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveAllRemoteCardsUseCase(repository: CardRepository): SaveAllRemoteCardsUseCase =
        SaveAllRemoteCardsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetCardWithIdUseCase(repository: CardRepository): GetCardWithIdUseCase =
        GetCardWithIdUseCase(repository)

}