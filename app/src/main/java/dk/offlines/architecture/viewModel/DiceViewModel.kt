package dk.offlines.architecture.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dk.offlines.architecture.LOG_TAG
import dk.offlines.architecture.R
import dk.offlines.architecture.util.DiceHelper

class DiceViewModel(app: Application): AndroidViewModel(app) {

    val headline = MutableLiveData<String>()
    val dice = MutableLiveData<IntArray>()
    private val context = app

    init {
        Log.i(LOG_TAG, "View model created!")
        headline.value = context.getString(R.string.welcome)
        dice.value = intArrayOf(1, 2, 3, 4, 5)
    }

    fun rollDice() {
        dice.value = DiceHelper.rollDice()
        headline.value = DiceHelper.evaluateDice(context, dice.value)
    }
}