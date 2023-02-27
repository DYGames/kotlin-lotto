package lotto.model

import lotto.entity.Money

enum class Rank(
    val countOfMatch: Int,
    val winningMoney: Money,
    val matchStrategy: ((countOfMatch: Int, isMatchBonus: Boolean) -> Boolean)
) {
    FIRST(6, Money(2_000_000_000), { countOfMatch, _ -> countOfMatch == 6 }),
    SECOND(5, Money(30_000_000), { countOfMatch, isMatchBonus -> countOfMatch == 5 && isMatchBonus }),
    THIRD(5, Money(1_500_000), { countOfMatch, _ -> countOfMatch == 5 }),
    FOURTH(4, Money(50_000), { countOfMatch, _ -> countOfMatch == 4 }),
    FIFTH(3, Money(5_000), { countOfMatch, _ -> countOfMatch == 3 }),
    MISS(0, Money(0), { countOfMatch, _ -> countOfMatch in (0..2) });

    companion object {
        private const val ERROR_DETERMINED_RANK_IS_EMPTY = "등수 판별에 실패하였습니다."

        fun determine(countOfMatch: Int, isMatchBonus: Boolean): Rank {
            val determinedRank = Rank.values().find { it.matchStrategy(countOfMatch, isMatchBonus) }
            checkNotNull(determinedRank) { ERROR_DETERMINED_RANK_IS_EMPTY }
            return determinedRank
        }
    }
}
