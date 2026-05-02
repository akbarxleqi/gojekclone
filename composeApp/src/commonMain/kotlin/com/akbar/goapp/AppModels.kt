package com.akbar.goapp

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(val label: String) {
    Home("Home"),
    Food("GoFood"),
    Ride("Ride"),
    Send("GoSend"),
    Mart("GoMart"),
    Resto("Resto"),
    Orders("Orders"),
    Promos("Promos"),
    Chat("Chat"),
    Profile("Profile"),
    Login("Login"),
    Register("Register")
}

data class Service(val name: String, val icon: ImageVector, val bg: Color, val tint: Color, val target: Screen)

data class Product(val title: String, val price: String, val oldPrice: String? = null, val badge: String? = null)

val foodDeals = listOf(
    Product("Pizza Margherita", "Rp24.000", "Rp60.000", "60% OFF"),
    Product("Double Cheeseburger", "Rp35.000", "Rp70.000", "50% OFF"),
    Product("Salmon Health Bowl", "Rp42.000", "Rp70.000", "40% OFF")
)

val martDeals = listOf(
    Product("Brokoli Segar 500g", "Rp18.000", "Rp22.500", "20% Off"),
    Product("Susu Cair Full Cream 1L", "Rp20.400", "Rp24.000", "15% Off"),
    Product("Daging Slice 250g", "Rp39.000", "Rp48.000", "19% Off")
)

val freshDeals = listOf(
    Product("Paket Masak Sayur Asem", "Rp12.000"),
    Product("Telur Ayam Negeri 10 btr", "Rp22.500")
)
