package io.github.mikaojk.day1.part2


import io.github.mikaojk.common.Elves
import io.github.mikaojk.common.elvesSortedByTotalCalories
import io.github.mikaojk.common.getFileAsString
import io.github.mikaojk.common.toItems
import io.github.mikaojk.common.totalCaloriesInItems

fun day1Part2(): Int {

    val itemsAsString = getFileAsString("src/main/resources/day1/items.txt")

    val elves = itemsAsString.split("\\n\\n".toRegex())
        .map { it.split("\\n".toRegex()) }.map {
            Elves(items = toItems(it))
        }

    val sortedElvesByTotalCalories = elvesSortedByTotalCalories(elves)


    return sumOfTopThreeElves(sortedElvesByTotalCalories)
}

fun sumOfTopThreeElves(elves: List<Elves>): Int {
    val elfHigestTotalCalories = totalCaloriesInItems(elves[0].items)
    val elfSecoundHigestTotalCalories = totalCaloriesInItems(elves[1].items)
    val elfThirdHigestTotalCalories = totalCaloriesInItems(elves[2].items)

    return (elfHigestTotalCalories + elfSecoundHigestTotalCalories + elfThirdHigestTotalCalories)

}

