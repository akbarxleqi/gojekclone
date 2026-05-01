package com.akbar.goapp.features.food

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akbar.goapp.Blue
import com.akbar.goapp.FoodRed
import com.akbar.goapp.Screen
import com.akbar.goapp.Service
import com.akbar.goapp.AppBottomBar
import com.akbar.goapp.CircleIcon
import com.akbar.goapp.FloatingCartBar
import com.akbar.goapp.IconBubble
import com.akbar.goapp.ProductCard
import com.akbar.goapp.RestaurantRow
import com.akbar.goapp.SearchPill
import com.akbar.goapp.foodDeals

@Composable
fun FoodScreen(open: (Screen) -> Unit) {
    Box(Modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(bottom = 176.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {
            item { FoodHeader { open(Screen.Home) } }
            item { FoodCategories() }
            item { FlashSaleSection() }
            item { RestaurantList() }
        }
        FloatingCartBar("3 items", "Dari 2 Resto", "Rp81.000", Modifier.align(Alignment.BottomCenter).padding(bottom = 82.dp, start = 16.dp, end = 16.dp))
        AppBottomBar(selected = "Home", onHome = { open(Screen.Home) }, onOrders = {}, onWallet = {}, onInbox = {}, onAccount = { open(Screen.Profile) })
    }
}

@Composable
fun FoodHeader(onBack: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(FoodRed)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CircleIcon(Icons.Default.ArrowBack, Color.White.copy(alpha = 0.18f), Color.White, Modifier.clickable { onBack() })
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text("GoFood", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Black)
                Text("Delivering to Home", color = Color.White.copy(alpha = 0.85f), fontWeight = FontWeight.SemiBold)
            }
            CircleIcon(Icons.Default.Favorite, Color.White.copy(alpha = 0.12f), Color.White)
            Spacer(Modifier.width(10.dp))
            CircleIcon(Icons.Default.Search, Color.White.copy(alpha = 0.12f), Color.White)
        }
        SearchPill("Mau makan apa hari ini?", Icons.Default.Restaurant)
    }
}

@Composable
fun FoodCategories() {
    val cats = listOf(
        Service("Resto\nTerdekat", Icons.Default.Restaurant, Color(0xFFFFF1F2), FoodRed, Screen.Food),
        Service("Ongkir\nMurah", Icons.Default.LocalShipping, Color(0xFFFFF7ED), Color(0xFFEA580C), Screen.Food),
        Service("Rating Tinggi", Icons.Default.Star, Color(0xFFFEFCE8), Color(0xFFCA8A04), Screen.Food),
        Service("Promo Gajian", Icons.Default.Star, Color(0xFFEFF6FF), Blue, Screen.Food)
    )
    Row(Modifier.padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        cats.forEach {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(84.dp)) {
                IconBubble(it.icon, it.bg, it.tint, Modifier.size(56.dp), 28)
                Spacer(Modifier.height(6.dp))
                Text(it.name, textAlign = TextAlign.Center, fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun FlashSaleSection() {
    Column {
        Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text("Flash Sale Gajian", fontSize = 22.sp, fontWeight = FontWeight.Black)
                Text("Hanya hari ini, buruan sikat!", color = Color(0xFF334155), fontSize = 16.sp)
            }
            Text(
                "02:45:12",
                color = Color.White,
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(Color(0xFFC30012))
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            )
        }
        Spacer(Modifier.height(14.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(18.dp)) {
            items(foodDeals) { item -> ProductCard(item, redPrice = true) }
        }
    }
}

@Composable
fun RestaurantList() {
    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Resto Terpopuler", fontSize = 22.sp, fontWeight = FontWeight.Black)
        RestaurantRow("Steakhouse Premium Central", "4.8 - 1.2km - 25 mnt", "PROMO", "FREE DELIVERY")
        RestaurantRow("Pasta Brava Italian", "4.6 - 0.8km - 15 mnt", "PROMO", null)
    }
}
