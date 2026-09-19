package com.dava.question_2_gavin

import kotlin.random.Random

class Monster {
    var element: Element
    var name: String
    var maxHp = 40
    var hp = 40

    init {
        val roll = Random.nextInt(3)
        if (roll == 0) element = Element.FIRE
        else if (roll == 1) element = Element.WATER
        else element = Element.GRASS
        name = element.monsterName()
    }

    fun attack(player: Player) {
        player.hp = player.hp - 10
        if (player.hp < 0) player.hp = 0
    }
}
