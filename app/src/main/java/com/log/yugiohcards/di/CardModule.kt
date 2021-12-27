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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
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
        api: CardApi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): CardRepository {
        return CardRepositoryImpl(
            api = api,
            dao = db.dao,
            ioDispatcher = ioDispatcher,
        )
    }

    @Provides
    @Singleton
    fun provideGetRandomCardUseCase(repository: CardRepository): GetRandomCardUseCase =
        GetRandomCardUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveAllRemoteCardsUseCase(
        repository: CardRepository,
        @ApplicationScope externalScope: CoroutineScope,
    ): SaveAllRemoteCardsUseCase =
        SaveAllRemoteCardsUseCase(
            repository = repository,
            externalScope = externalScope,
        )

    @Provides
    @Singleton
    fun provideGetCardWithIdUseCase(repository: CardRepository): GetCardWithIdUseCase =
        GetCardWithIdUseCase(repository)

}