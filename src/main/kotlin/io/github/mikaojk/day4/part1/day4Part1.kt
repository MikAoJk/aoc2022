package io.github.mikaojk.day4.part1

import io.github.mikaojk.common.getFileAsString

fun day4Part1(): Int {

    val sectionsToClean = getFileAsString("src/main/resources/day4/input.txt")
    val elvePair = sectionsToClean.split("\\n".toRegex()).map {
        val sectionsForFirstElve = it.substringBefore(",")
        val sectionsForSecoundElve = it.substringAfter(",")
        ElvePair(
            firstElve = Section(
                from = sectionsForFirstElve.substringBefore('-').toInt(),
                to = sectionsForFirstElve.substringAfter('-').toInt()
            ),
            secoundElve = Section(
                from = sectionsForSecoundElve.substringBefore('-').toInt(),
                to = sectionsForSecoundElve.substringAfter('-').toInt()
            )
        )
    }

    var inSameFullyRange = 0
    elvePair.forEach {
        if (inSameFullyRange(it.firstElve, it.secoundElve)) {
            inSameFullyRange++
        }
    }


    return inSameFullyRange

}

fun inSameFullyRange(firstSection: Section, secoundSection: Section): Boolean {

    return (firstSection.to in secoundSection.range() && firstSection.from in secoundSection.range())
            || (secoundSection.to in firstSection.range() && secoundSection.from in firstSection.range())

}

fun Section.range(): ClosedRange<Int> = from.rangeTo(to)


data class ElvePair(
    val firstElve: Section,
    val secoundElve: Section
)

data class Section(
    val from: Int,
    val to: Int
)