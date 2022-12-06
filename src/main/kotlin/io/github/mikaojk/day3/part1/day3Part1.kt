package io.github.mikaojk.day3.part1

import io.github.mikaojk.common.getFileAsString
import java.util.Locale

fun day3Part1(): Int {

    val rucksacksItems = getFileAsString("src/main/resources/day3/rucksacksItems.txt")
    val rucksacks = rucksacksItems.split("\\n".toRegex()).map {
        Rucksack(
            firstCompartment = Items(items = it.slice(0 until (it.length / 2))),
            secoundCompartment = Items(items = it.slice((it.length / 2) until (it.length)))
        )
    }

    val sameItems = rucksacks.map { rucksack ->
        findSameItem(rucksack.firstCompartment, rucksack.secoundCompartment)
    }

    val sumForEachRucksack = sameItems.flatMap { charList ->
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

fun findSameItem(items1: Items, items2: Items): List<Char> {
    val items1CharArray = items1.items.toCharArray()
    val distinctSameItem = items1CharArray.filter { item -> items2.items.contains(item) }.toSet().toList();

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
    val firstCompartment: Items,
    val secoundCompartment: Items
)

data class Items(
    val items: String
)