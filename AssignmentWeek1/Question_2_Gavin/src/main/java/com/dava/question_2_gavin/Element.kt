package com.dava.question_2_gavin

enum class Element {
    FIRE, WATER, GRASS;

    fun beats(): Element {
        if (this == FIRE) return GRASS
        if (this == WATER) return FIRE
        return WATER
    }

    fun monsterName(): String {
        if (this == FIRE) return "Firemon"
        if (this == WATER) return "Watermon"
        return "Grassmon"
    }
}
