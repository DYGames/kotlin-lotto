package lotto.model

import lotto.entity.Lotto

class InputLottoGenerator(val readLotto: () -> Lotto) : LottoGenerator {
    override fun generate(): Lotto {
        return readLotto()
    }
}
