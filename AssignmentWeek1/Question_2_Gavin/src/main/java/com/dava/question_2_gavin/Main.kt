package com.dava.question_2_gavin

fun main() {
    println("================================")
    println("       WIZARD ADVENTURE")
    println("================================")

    print("What's your name? ")
    var name = readln().trim()
    while (name.isEmpty()) {
        println("Name cannot be empty.")
        print("What's your name? ")
        name = readln().trim()
    }

    val player = Player(name)
    println("Good luck, $name! You're gonna need it!")

    while (true) {
        println()
        println("What're you going to do?")
        println("1. View Stats")
        println("2. Enter battle")
        println("3. Exit")
        print("Choose: ")
        val choice = readln().trim()

        if (choice == "1") {
            viewStats(player)
        } else if (choice == "2") {
            enterBattle(player)
        } else if (choice == "3") {
            println("Farewell, ${player.name}!")
            return
        } else {
            println("Invalid choice.")
        }
    }
}

fun viewStats(player: Player) {
    var back = false
    while (!back) {
        println()
        println("--- ${player.name}'s STATS ---")
        println("HP: ${player.hp}/ ${player.maxHp}")
        println("Mana: ${player.mp}/ ${player.maxMp}")
        println("Kills needed to evolve: ${player.kills}/ 5")
        println("Mana Potions held: ${player.mpPotions}")
        println("Health Potions held: ${player.hpPotions}")
        if (player.isStrong) {
            println("Lifesteal: ${player.lifesteal}")
        }
        println("-----------------")

        println("a. Drink Mana Potion")
        println("b. Drink Health Potion")
        println("c. Rename self")
        println("d. Back")
        print("Choose: ")
        val sub = readln().trim().lowercase()

        if (sub == "a") {
            println(player.drinkMpPotion())
        } else if (sub == "b") {
            println(player.drinkHpPotion())
        } else if (sub == "c") {
            print("New name: ")
            val newName = readln().trim()
            if (newName.isEmpty()) {
                println("Name cannot be empty.")
            } else {
                player.name = newName
                println("Renamed to ${player.name}.")
            }
        } else if (sub == "d") {
            back = true
        } else {
            println("Invalid choice.")
        }
    }
}

fun enterBattle(player: Player) {
    val monster = Monster()
    var result = ""

    while (result == "") {
        println()
        println("--- BATTLE ---")
        println("${player.name}")
        println("HP: ${player.hp}/ ${player.maxHp}")
        println("Mana: ${player.mp}/ ${player.maxMp}")
        println("HP Potions: ${player.hpPotions}")
        println("MP Potions: ${player.mpPotions}")
        println("${monster.name}")
        println("HP: ${monster.hp}/ ${monster.maxHp}")
        println("Type: ${monster.element.name.lowercase()}")
        println("----------")

        println()
        println("a. Fire Attack")
        println("b. Water Attack")
        println("c. Grass Attack")
        println("d. Drink potion")
        println("e. Run")
        print("Choose: ")
        val action = readln().trim().lowercase()

        var spell: Element? = null
        if (action == "a") spell = Element.FIRE
        else if (action == "b") spell = Element.WATER
        else if (action == "c") spell = Element.GRASS

        if (spell != null) {
            if (player.mp < 10) {
                println("Not enough mana. (need 10)")
                continue
            }
            player.mp = player.mp - 10

            var damage = 10
            if (spell.beats() == monster.element) {
                damage = damage * 2
                println("It's super effective!")
            }
            damage = (damage * player.damageMultiplier()).toInt()

            monster.hp = monster.hp - damage
            if (monster.hp < 0) monster.hp = 0
            println("${player.name} casts ${spell.name} for $damage damage.")

            if (player.isStrong) {
                player.hp = player.hp + player.lifesteal
                if (player.hp > player.maxHp) player.hp = player.maxHp
                println("Lifesteal heals ${player.lifesteal} HP.")
            }

            if (monster.hp <= 0) {
                result = "win"
            } else {
                monster.attack(player)
                println("${monster.name} attacks for 10.")
                if (player.hp <= 0) result = "lose"
            }

        } else if (action == "d") {
            var inPotionMenu = true
            while (inPotionMenu) {
                println()
                println("--- POTION MENU ---")
                println("HP: ${player.hp}/ ${player.maxHp}")
                println("Mana: ${player.mp}/ ${player.maxMp}")
                println("HP Potions: ${player.hpPotions}")
                println("MP Potions: ${player.mpPotions}")
                println("----------")
                println("1. Drink HP Potion")
                println("2. Drink MP Potion")
                println("3. Back")
                print("Choose: ")
                val pick = readln().trim()

                if (pick == "1") {
                    println(player.drinkHpPotion())
                    if (monster.hp > 0) {
                        monster.attack(player)
                        println("${monster.name} attacks for 10.")
                        if (player.hp <= 0) result = "lose"
                    }
                    inPotionMenu = false

                } else if (pick == "2") {
                    println(player.drinkMpPotion())
                    if (monster.hp > 0) {
                        monster.attack(player)
                        println("${monster.name} attacks for 10.")
                        if (player.hp <= 0) result = "lose"
                    }
                    inPotionMenu = false

                } else if (pick == "3") {
                    inPotionMenu = false
                    println("Back to battle.")

                } else {
                    println("Invalid choice.")
                }
            }

        } else if (action == "e") {
            result = "flee"
            println("You ran away!")

        } else {
            println("Invalid choice.")
        }
    }

    if (result == "win") {
        println()
        println("${monster.name} defeated!")
        val evolved = player.registerKill()
        println("Kills: ${player.kills}/ 5")
        if (evolved) {
            println("You evolved into a STRONG WIZARD!")
            println("HP/MP scaled 1.5x. Lifesteal unlocked.")
        } else if (player.isStrong) {
            println("Lifesteal increased to ${player.lifesteal}.")
        }
    } else if (result == "lose") {
        println()
        println("You died! Restarting from the beginning...")
        player.maxHp = 50
        player.hp = 50
        player.maxMp = 30
        player.mp = 30
        player.hpPotions = 5
        player.mpPotions = 5
        player.kills = 0
        player.isStrong = false
        player.lifesteal = 1
    }
}