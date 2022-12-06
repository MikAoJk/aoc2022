package io.github.mikaojk.day2.part2

import io.github.mikaojk.common.getFileAsString
import io.github.mikaojk.day2.part1.Hand
import io.github.mikaojk.day2.part1.Player
import io.github.mikaojk.day2.part1.Round
import io.github.mikaojk.day2.part1.calculateScore

fun day2Part2(): Int {

    val opponent = Player(score = 0)
    val you = Player(score = 0)

    val game = getFileAsString("src/main/resources/day2/game.txt")
    val rounds = game.split("\\n".toRegex()).map { round ->
        val opptentHand = Hand.values().find { it.aliasOpponent == round[0].toString() }!!
        Round(
            opponentHand = opptentHand,
            yourHand = decideYourHand(guideOutCome = round[2].toString(), hand = opptentHand) ,
            opponent = opponent,
            you = you
        )
    }

    rounds.forEach { round ->
        calculateScore(round.opponent, round.opponentHand, round.you, round.yourHand)
    }


    return you.score
}

fun decideYourHand(guideOutCome : String, hand: Hand): Hand {
    when (hand) {
        Hand.ROCK -> {
            return if (guideOutCome == "X") {
                Hand.SCISSORS
            } else if (guideOutCome == "Z") {
                Hand.PAPER
            } else if (guideOutCome == "Y") {
                Hand.ROCK
            } else {
                throw RuntimeException("Unknow guideOutCome: $guideOutCome")
            }
        }
        Hand.PAPER -> {
            return if (guideOutCome == "X") {
                Hand.ROCK
            } else if (guideOutCome == "Z") {
                Hand.SCISSORS
            } else if (guideOutCome == "Y") {
                Hand.PAPER
            } else {
                throw RuntimeException("Unknow guideOutCome: $guideOutCome")
            }
        }
        else -> {
            return if (guideOutCome == "X") {
                Hand.PAPER
            } else if (guideOutCome == "Z") {
                Hand.ROCK
            } else if (guideOutCome == "Y") {
                Hand.SCISSORS
            } else {
                throw RuntimeException("Unknow guideOutCome: $guideOutCome")
            }
        }
    }

}


