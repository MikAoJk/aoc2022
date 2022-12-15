package io.github.mikaojk.day3.part2

import io.github.mikaojk.common.getFileAsString
import java.util.Locale

fun day3Part2(): Int {

    val rucksacksItems = getFileAsString("src/main/resources/day3/rucksacksItems.txt")
    val rucksacks = rucksacksItems.split("\\n".toRegex()).mapIndexed { index, rucksacksItems ->
        Rucksack(
            number = index,
            firstCompartment = Items(items = rucksacksItems.slice(0 until (rucksacksItems.length / 2))),
            secoundCompartment = Items(items = rucksacksItems.slice((rucksacksItems.length / 2) until (rucksacksItems.length)))
        )
    }

    val groupedRucksacks = rucksacks.chunked(3)

    val sameItemsInGroup = groupedRucksacks.map {
        findSameItem(it)
    }

    val sumForEachRucksack = sameItemsInGroup.flatMap { charList ->
        charList.map { char ->
            findPriorityValueForItem(char)
        }
    }


    return sumForEachRucksack.sum()
}


fun findPriorityValueForItem(item: Char): Int {
    return if (item.isUpperCase()) {
        PriorityUpperCase.values().find { it.name == item.toString() }!!.value
    } else {
        (PriorityUpperCase.values().find { it.name == (item.uppercase(Locale.getDefault())) }!!.value) - 26
    }
}

fun findSameItem(rucksacks: List<Rucksack>): List<Char> {

    val rucksacksItem = rucksacks.map { rucksack ->
        rucksack.firstCompartment.items + rucksack.secoundCompartment.items
    }

    return findSameItem(rucksacksItem[0], rucksacksItem[1], rucksacksItem[2])
}

fun findSameItem(items1: String, items2: String, items3: String): List<Char> {
    val items1CharArray = items1.toCharArray()
    val distinctSameItem =
        items1CharArray.filter { item -> (items2.contains(item) && items3.contains(item)) }.toSet().toList();

    return distinctSameItem
}

enum class PriorityUpperCase(val value: Int) {
    A(27),
    B(28),
    C(29),
    D(30),
    E(31),
    F(32),
    G(33),
    H(34),
    I(35),
    J(36),
    K(37),
    L(38),
    M(39),
    N(40),
    O(41),
    P(42),
    Q(43),
    R(44),
    S(45),
    T(46),
    U(47),
    V(48),
    W(49),
    X(50),
    Y(51),
    Z(52)
}

data class Rucksack(
    val number: Int,
    val firstCompartment: Items,
    val secoundCompartment: Items
)

data class Items(
    val items: String
)