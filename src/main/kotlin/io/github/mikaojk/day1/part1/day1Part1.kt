package io.github.mikaojk.day1.part1


import io.github.mikaojk.common.Elves
import io.github.mikaojk.common.elfWithBiggestTotalCalories
import io.github.mikaojk.common.getFileAsString
import io.github.mikaojk.common.toItems
import io.github.mikaojk.common.totalCaloriesInItems

fun day1Part1(): Int {

    val itemsAsString = getFileAsString("src/main/resources/day1/items.txt")

    val elves = itemsAsString.split("\\n\\n".toRegex())
        .map { it.split("\\n".toRegex()) }.map {
            Elves(items = toItems(it))
        }

    val elveWithBiggestTotalCalories = elfWithBiggestTotalCalories(elves)


    return totalCaloriesInItems(elveWithBiggestTotalCalories.items)
}