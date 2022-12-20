package io.github.mikaojk.day4.part2

import io.github.mikaojk.common.getFileAsString


fun day4Part2(): Int {

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

    var inOverlapsRange = 0
    elvePair.forEach {
        if (inOverlapsRange(it.firstElve, it.secoundElve)) {
            inOverlapsRange++
        }
    }


    return inOverlapsRange

}

fun inOverlapsRange(firstSection: Section, secoundSection: Section): Boolean {
    return (firstSection.to in secoundSection.range() || firstSection.from in secoundSection.range())
            || (secoundSection.to in firstSection.range() || secoundSection.from in firstSection.range())
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