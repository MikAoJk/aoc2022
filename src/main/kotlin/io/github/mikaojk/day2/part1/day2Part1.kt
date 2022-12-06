package io.github.mikaojk.day2.part1

import io.github.mikaojk.common.getFileAsString

fun day2Part1(): Int {

    val opponent = Player(score = 0)
    val you = Player(score = 0)

    val game = getFileAsString("src/main/resources/day2/game.txt")
    val rounds = game.split("\\n".toRegex()).map { round ->
        Round(
            opponentHand = Hand.values().find { it.aliasOpponent == round[0].toString() }!!,
            yourHand = Hand.values().find { it.aliasYour == round[2].toString() }!!,
            opponent = opponent,
            you = you
        )
    }

    rounds.forEach { round ->
        calculateScore(round.opponent, round.opponentHand, round.you, round.yourHand)
    }


    return you.score
}


fun calculateScore(opponentPlayer: Player, opponentHand: Hand, youPlayer: Player, yourHand: Hand) {
    if (outComeHands(opponentHand, yourHand) == OutCome.YOUWIN) {
        youPlayer.score += (6 + yourHand.value)
        opponentPlayer.score += opponentHand.value
    } else if (outComeHands(opponentHand, yourHand) == OutCome.OPPONENTWIN) {
        opponentPlayer.score += (6 + opponentHand.value)
        youPlayer.score += yourHand.value
    } else {
        opponentPlayer.score += (3 + opponentHand.value)
        youPlayer.score += (3 + yourHand.value)
    }

}

fun outComeHands(opponentHand: Hand, yourHand: Hand): OutCome {
    return if (opponentHand == Hand.ROCK && yourHand == Hand.PAPER) {
        OutCome.YOUWIN
    } else if (opponentHand == Hand.ROCK && yourHand == Hand.SCISSORS) {
        OutCome.OPPONENTWIN
    } else if (opponentHand == Hand.PAPER && yourHand == Hand.ROCK) {
        OutCome.OPPONENTWIN
    } else if (opponentHand == Hand.PAPER && yourHand == Hand.SCISSORS) {
        OutCome.YOUWIN
    } else if (opponentHand == Hand.SCISSORS && yourHand == Hand.ROCK) {
        OutCome.YOUWIN
    } else if (opponentHand == Hand.SCISSORS && yourHand == Hand.PAPER) {
        OutCome.OPPONENTWIN
    } else {
        OutCome.DRAW
    }
}

enum class OutCome {
    OPPONENTWIN,
    YOUWIN,
    DRAW
}

data class Player(
    var score: Int
)

data class Round(
    val opponentHand: Hand,
    val yourHand: Hand,
    val opponent: Player,
    val you: Player
)


enum class Hand(val aliasOpponent: String, val aliasYour: String, val value: Int) {
    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3)
}