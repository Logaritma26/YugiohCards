package com.log.yugiohcards.lib.domain.use_case.card

import com.log.yugiohcards.lib.domain.model.card.Card
import javax.inject.Inject

class GetFilteredCards @Inject constructor() {
    operator fun invoke(
        allCards: List<Card>,
        query: String?,
        attribute: String?,
        archetype: String?,
        type: String?,
        race: String?,
    ): List<Card> {
        var cards: List<Card> = allCards
        query?.let { it ->
            cards = cards.filter { card ->
                card.name.contains(other = it, ignoreCase = true)
            }
        }
        attribute?.let { it ->
            cards = cards.filter { card ->
                if (card.details.attribute != null) {
                    card.details.attribute.contains(other = it, ignoreCase = true)
                } else {
                    false
                }
            }
        }
        archetype?.let { it ->
            cards = cards.filter { card ->
                if (card.details.archetype != null) {
                    card.details.archetype.contains(other = it, ignoreCase = true)
                } else {
                    false
                }
            }
        }
        type?.let { it ->
            cards = cards.filter { card ->
                card.details.type.contains(other = it, ignoreCase = true)
            }
        }
        race?.let { it ->
            cards = cards.filter { card ->
                card.details.race.contains(other = it, ignoreCase = true)
            }
        }

        return cards
    }
}