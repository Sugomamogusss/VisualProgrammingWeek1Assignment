package com.dava.question1feli

class Order(val customer: String) {
    val items = ArrayList<MenuItem>()
    val quantities = ArrayList<Int>()

    fun addItem(item: MenuItem, qty: Int) {
        items.add(item)
        quantities.add(qty)
    }

    fun total(): Int {
        var sum = 0
        for (i in items.indices) {
            sum += items[i].price * quantities[i]
        }
        return sum
    }

    fun show() {
        println("$customer's ORDER")
        for (i in items.indices) {
            val line = items[i].price * quantities[i]
            println("${i + 1}. ${items[i].name} x${quantities[i]} \$$line")
        }
        println()
        println("TOTAL \$${total()}")
    }
}