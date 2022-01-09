package com.log.yugiohcards.lib.domain.use_case.card

import com.log.yugiohcards.lib.domain.repository.CardRepository
import javax.inject.Inject

class GetFilterListUseCase @Inject constructor(
    private val repository: CardRepository,
) {
    suspend operator fun invoke(): Map<FilterType, ArrayList<String>> {

        val listMap: Map<FilterType, ArrayList<String?>> = mapOf(
            FilterType.ATTRIBUTE to arrayListOf(),
            FilterType.ARCHETYPE to arrayListOf(),
            FilterType.RACE to arrayListOf(),
            FilterType.TYPE to arrayListOf(),
        )

        repository.getAllCards().map { cardEntity ->
            listMap[FilterType.ATTRIBUTE]!!.add(cardEntity.attribute)
            listMap[FilterType.ARCHETYPE]!!.add(cardEntity.archetype)
            listMap[FilterType.RACE]!!.add(cardEntity.race)
            listMap[FilterType.TYPE]!!.add(cardEntity.type)
        }

        val resMap: Map<FilterType, ArrayList<String>> = mapOf(
            FilterType.ATTRIBUTE to arrayListOf(),
            FilterType.ARCHETYPE to arrayListOf(),
            FilterType.RACE to arrayListOf(),
            FilterType.TYPE to arrayListOf(),
        )

        listMap.map {
            resMap[it.key]!!.addAll(
                listOfNotNull(*it.value.toTypedArray()).distinct().sorted()
            )
        }
        return resMap
    }

    enum class FilterType {
        ATTRIBUTE,
        ARCHETYPE,
        RACE,
        TYPE,
        QUERY,
    }
}


