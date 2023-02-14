package lotto.model

class Lottos(inputMoney: Int, lottoPrice: Int, lottoGenerator: LottoGenerator) {
    val value: List<Lotto>

    init {
        val count = inputMoney / lottoPrice
        value = (0 until count).map {
            lottoGenerator.generate()
        }
    }
}
