package com.akbar.goapp.features.mart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akbar.goapp.Blue
import com.akbar.goapp.FoodRed
import com.akbar.goapp.Orange
import com.akbar.goapp.Screen
import com.akbar.goapp.Service
import com.akbar.goapp.TextMuted
import com.akbar.goapp.AppBottomBar
import com.akbar.goapp.FloatingCartBar
import com.akbar.goapp.IconBubble
import com.akbar.goapp.ProductRowSection
import com.akbar.goapp.SearchPill
import com.akbar.goapp.SimpleTopBar
import com.akbar.goapp.freshDeals
import com.akbar.goapp.martDeals

@Composable
fun MartScreen(open: (Screen) -> Unit) {
    Box(Modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(bottom = 132.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            item { SimpleTopBar("GoMart", Icons.Default.ArrowBack, Icons.Default.Search, topPadding = 0.dp) { open(Screen.Home) } }
            item { SearchPill("Cari kebutuhan harianmu", Icons.Default.Search, Modifier.padding(horizontal = 16.dp)) }
            item { MartCategories() }
            item { GroceryBanner() }
            item { ProductRowSection("Promo Spesial", martDeals, true) }
            item { ProductRowSection("Pilihan Segar Hari Ini", freshDeals, false) }
        }
        FloatingCartBar("2 item | Rp45.000", "Dari Toko Terdekat", "Lihat Keranjang", Modifier.align(Alignment.BottomCenter).padding(bottom = 82.dp, start = 16.dp, end = 16.dp))
        AppBottomBar(
        selected = "Home",
        onHome = { open(Screen.Home) },
        onPromos = { open(Screen.Promos) },
        onOrders = { open(Screen.Orders) },
        onChat = { open(Screen.Chat) }
    )
    }
}

@Composable
fun MartCategories() {
    val items = listOf(
        Service("Sayur & Buah", Icons.Default.Storefront, Color(0xFFE9FFF6), Color(0xFF009B68), Screen.Mart),
        Service("Daging & Seafood", Icons.Default.Restaurant, Color(0xFFFFF0F1), FoodRed, Screen.Mart),
        Service("Susu & Telur", Icons.Default.ShoppingBasket, Color(0xFFEFF6FF), Blue, Screen.Mart),
        Service("Snack", Icons.Default.Star, Color(0xFFFEFCE8), Orange, Screen.Mart),
        Service("Kebutuhan Rumah", Icons.Default.Home, Color(0xFFF5F3FF), Color(0xFF9333EA), Screen.Mart),
        Service("Lainnya", Icons.Default.GridView, Color(0xFFF8FAFC), TextMuted, Screen.Mart)
    )
    Column(Modifier.padding(horizontal = 16.dp)) {
        Text("Pilih Kategori", fontSize = 20.sp, fontWeight = FontWeight.Black)
        Spacer(Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.height(138.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = false
        ) {
            items(items) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconBubble(it.icon, it.bg, it.tint, Modifier.size(50.dp), 24)
                    Spacer(Modifier.height(6.dp))
                    Text(it.name, fontSize = 11.sp, textAlign = TextAlign.Center, lineHeight = 13.sp)
                }
            }
        }
    }
}

@Composable
fun GroceryBanner() {
    Box(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.linearGradient(listOf(Color(0xFF0B3D2E), Color(0xFF2F855A), Color(0xFFF59E0B))))
            .padding(22.dp)
    ) {
        Text("Diskon s/d 50%", color = Color.White, fontSize = 16.sp, modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(FoodRed).padding(horizontal = 8.dp, vertical = 4.dp))
        Text("Belanja Mingguan\nLebih Hemat!", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Black, modifier = Modifier.align(Alignment.BottomStart))
    }
}
