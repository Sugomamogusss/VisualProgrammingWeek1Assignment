package com.dava.question1feli

class MenuItem(var name: String, var price: Int) {
    fun show(number: Int) {
        println("$number. $name  \$$price")
    }
}
