package io.github.mikaojk.common

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

fun elfWithBiggestTotalCalories(elves: List<Elves>): Elves {
    return elves.maxBy { totalCaloriesInItems(it.items) }
}

fun elvesSortedByTotalCalories(elves: List<Elves>): List<Elves> {
    return elves.sortedByDescending { totalCaloriesInItems(it.items) }
}

fun toItems(items: List<String>): List<Items> {
    return items.map { item ->
        Items(item.toInt())
    }
}

fun totalCaloriesInItems(items: List<Items>) = items.sumOf { it.calories }

fun getFileAsString(filePath: String): String {
    try {
        return String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8)
    } catch (exception: Exception) {
        throw Exception("Could not get file content as string", exception)
    }
}

data class Elves(
    val items: List<Items>
)

data class Items(
    val calories: Int
)