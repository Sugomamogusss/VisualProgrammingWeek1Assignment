package com.dava.question_2_gavin

class Player(var name: String) {
    var maxHp = 50
    var hp = 50
    var maxMp = 30
    var mp = 30
    var hpPotions = 5
    var mpPotions = 5
    var kills = 0
    var isStrong = false
    var lifesteal = 1

    fun showStats() {
        println("--------------------------------")
        println("$name's STATS")
        println("HP: $hp/$maxHp")
        println("Mana: $mp/$maxMp")
        println("Kills needed to evolve: $kills/5")
        println("Mana Potions held: $mpPotions")
        println("Health Potions held: $hpPotions")
        if (isStrong) {
            println("Lifesteal: $lifesteal")
            println("Status: STRONG WIZARD")
        }
        println("--------------------------------")
    }

    fun drinkHpPotion(): String {
        if (hpPotions <= 0) return "No Health Potions left."
        if (hp >= maxHp) return "HP is already full."
        hpPotions--
        hp = hp + 25
        if (hp > maxHp) hp = maxHp
        return "Drank Health Potion. HP = $hp/$maxHp"
    }

    fun drinkMpPotion(): String {
        if (mpPotions <= 0) return "No Mana Potions left."
        if (mp >= maxMp) return "MP is already full."
        mpPotions--
        mp = mp + 15
        if (mp > maxMp) mp = maxMp
        return "Drank Mana Potion. MP = $mp/$maxMp"
    }

    fun registerKill(): Boolean {
        kills++
        if (kills >= 5 && !isStrong) {
            isStrong = true
            maxHp = (maxHp * 1.5).toInt()
            maxMp = (maxMp * 1.5).toInt()
            hp = maxHp
            mp = maxMp
            lifesteal = 1
            return true
        }
        if (isStrong) lifesteal++
        return false
    }

    fun damageMultiplier(): Double {
        if (isStrong) return 1.5
        return 1.0
    }
}
