package dk.offlines.architecture.util

import android.content.Context
import dk.offlines.architecture.R
import kotlin.random.Random

class DiceHelper {
    companion object {

        private fun getDie(): Int {
            return Random.nextInt(1, 7)
        }

        fun rollDice(): IntArray {
            return intArrayOf(
                getDie(),
                getDie(),
                getDie(),
                getDie(),
                getDie()
            )
        }

        fun evaluateDice(context: Context, dice: IntArray?): String {
            val result = mutableMapOf(
                Pair(1, 0),
                Pair(2, 0),
                Pair(3, 0),
                Pair(4, 0),
                Pair(5, 0),
                Pair(6, 0)
            )

            for (i in 0 until dice!!.size) {
                val currentCount = result.getOrElse(dice[i]) { 0 }
                result[dice[i]] = currentCount + 1
            }

            return when {
                result.containsValue(5) ->
                    context.getString(R.string.five_of_a_kind)
                result.containsValue(4) ->
                    context.getString(R.string.four_of_a_kind)
                isFullHouse(result) ->
                    context.getString(R.string.full_house)
                isStraight(dice) ->
                    context.getString(R.string.straight)
                result.containsValue(3) ->
                    context.getString(R.string.three_of_a_kind)
                is2Pairs(result.values) ->
                    context.getString(R.string.two_pairs)
                result.containsValue(2) ->
                    context.getString(R.string.pair)
                else ->
                    context.getString(R.string.nothing_special)

            }
        }

        private fun is2Pairs(values: MutableCollection<Int>): Boolean {
            var foundPair = false
            for (value in values) {
                if(value == 2) {
                    if(foundPair) return true else foundPair = true
                }
            }
            return false
        }

        private fun isStraight(dice: IntArray): Boolean {
            return (dice.contains(1) || dice.contains(6)) &&
                    dice.contains(2) &&
                    dice.contains(3) &&
                    dice.contains(4) &&
                    dice.contains(5)
        }

        private fun isFullHouse(result: MutableMap<Int, Int>): Boolean {
            return result.containsValue(3) && result.containsValue(2)
        }
    }
}