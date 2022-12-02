package io.github.mikaojk.day2

import io.github.mikaojk.common.getFileAsString

fun day2(): Int {

    val gameguide = getFileAsString("src/main/resources/day2/gameguide.txt")

    return 200
}

fun calculateScore() {

}

data class Player(
    val score: Int,
    val winner: Boolean
)

data class Game(
    val player1: Player,
    val player2: Player,
    val rounds: List<Round>
)

data class Round(
    val roundNumber: Int
)

enum class HandShape(val alias: String) {
    ROCK("A"),
    PAPER("B"),
    SCISSORS("C")
}