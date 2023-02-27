package lotto.controller

import lotto.entity.Lotto
import lotto.entity.LottoCount
import lotto.entity.PurchasedLottos
import lotto.misc.tryAndRerun
import lotto.model.InputLottoGenerator
import lotto.model.LottoMachine
import lotto.model.RandomLottoGenerator
import lotto.view.InputView

class LottoController(private val inputView: InputView) {
    private val manualLottoGenerator = InputLottoGenerator { readLotto() }
    private val autoLottoGenerator = RandomLottoGenerator()
    private val lottoMachine = LottoMachine(manualLottoGenerator, autoLottoGenerator)

    private fun readLotto(): Lotto {
        return tryAndRerun {
            inputView.readLotto()
        }
    }

    fun makePurchasedLottos(lottoManualCount: LottoCount, lottoAutoCount: LottoCount): PurchasedLottos {
        return lottoMachine.producePurchasedLottos(
            lottoManualCount, lottoAutoCount
        )
    }
}
