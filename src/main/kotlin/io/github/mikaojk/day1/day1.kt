package io.github.mikaojk.day1


import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

fun day1(): Int {

    val itemsAsString = getFileAsString("src/main/resources/day1/items.txt")

    val elves = itemsAsString.split("\\n\\n".toRegex())
        .map { it.split("\\n".toRegex()) }.map {
            Elves(items = toItems(it))
        }

    val elveWithBiggestTotalCalories = elveWithBiggestTotalCalories(elves)


    return totalCaloriesInItems(elveWithBiggestTotalCalories.items)
}


fun elveWithBiggestTotalCalories(elves: List<Elves>): Elves {
    return elves.maxBy { totalCaloriesInItems(it.items) }
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
