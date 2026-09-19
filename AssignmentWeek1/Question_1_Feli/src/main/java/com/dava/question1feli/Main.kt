package com.dava.question1feli

fun main() {
    val menu = ArrayList<MenuItem>()
    menu.add(MenuItem("Nasi Goreng", 150))
    menu.add(MenuItem("Mie Ayam", 120))
    menu.add(MenuItem("Es Teh", 30))
    menu.add(MenuItem("Ayam Bakar", 200))

    val orders = ArrayList<Order>()

    while (true) {
        println()
        println("ORDER SYSTEM")
        println("1. Make Order")
        println("2. View Orders")
        println("3. View Menu")
        println("4. Add Menu")
        println("5. Edit Menu")
        println("6. Delete Menu")
        println("7. Exit")
        print("Choose: ")
        val choice = readln().trim()

        if (choice == "1") {
            if (menu.isEmpty()) {
                println("Menu is empty, add items first.")
                continue
            }
            print("Customer name: ")
            val name = readln().trim()
            if (name.isEmpty()) {
                println("Name cannot be empty.")
                continue
            }

            val order = Order(name)
            while (true) {
                println()
                for (i in menu.indices) menu[i].show(i + 1)
                println("0. Done")
                print("Pick item number: ")
                val pick = readln().trim().toIntOrNull()
                if (pick == null) {
                    println("Invalid number.")
                    continue
                }
                if (pick == 0) break

                val index = pick - 1
                if (index < 0 || index >= menu.size) {
                    println("Item number out of range.")
                    continue
                }

                print("Quantity: ")
                val qty = readln().trim().toIntOrNull()
                if (qty == null || qty <= 0) {
                    println("Quantity must be a positive number.")
                    continue
                }
                order.addItem(menu[index], qty)
                println("Added.")
            }

            if (order.items.isEmpty()) {
                println("Order is empty, cancelled.")
            } else {
                orders.add(order)
                println()
                println("=== ORDER CONFIRMED ===")
                order.show()
            }

        } else if (choice == "2") {
            if (orders.isEmpty()) {
                println("No orders yet.")
            } else {
                for (o in orders) {
                    println("----------------------")
                    o.show()
                }
            }

        } else if (choice == "3") {
            if (menu.isEmpty()) println("Menu is empty.")
            else for (i in menu.indices) menu[i].show(i + 1)

        } else if (choice == "4") {
            print("Food name: ")
            val name = readln().trim()
            if (name.isEmpty()) {
                println("Name cannot be empty.")
                continue
            }
            print("Price: ")
            val price = readln().trim().toIntOrNull()
            if (price == null || price <= 0) {
                println("Price must be a positive number.")
                continue
            }
            menu.add(MenuItem(name, price))
            println("Menu added.")

        } else if (choice == "5") {
            if (menu.isEmpty()) {
                println("Menu is empty.")
                continue
            }
            for (i in menu.indices) menu[i].show(i + 1)
            print("Item number to edit: ")
            val pick = readln().trim().toIntOrNull()
            if (pick == null) {
                println("Invalid number.")
                continue
            }
            val index = pick - 1
            if (index < 0 || index >= menu.size) {
                println("Item number out of range.")
                continue
            }

            print("New name: ")
            val newName = readln().trim()
            if (newName.isEmpty()) {
                println("Name cannot be empty.")
                continue
            }
            print("New price: ")
            val newPrice = readln().trim().toIntOrNull()
            if (newPrice == null || newPrice <= 0) {
                println("Price must be a positive number.")
                continue
            }
            menu[index].name = newName
            menu[index].price = newPrice
            println("Menu updated.")

        } else if (choice == "6") {
            if (menu.isEmpty()) {
                println("Menu is empty.")
                continue
            }
            for (i in menu.indices) menu[i].show(i + 1)
            print("Item number to delete: ")
            val pick = readln().trim().toIntOrNull()
            if (pick == null) {
                println("Invalid number.")
                continue
            }
            val index = pick - 1
            if (index < 0 || index >= menu.size) {
                println("Item number out of range.")
                continue
            }
            menu.removeAt(index)
            println("Menu deleted.")

        } else if (choice == "7") {
            println("Goodbye!")
            return

        } else {
            println("Invalid choice.")
        }
    }
}
