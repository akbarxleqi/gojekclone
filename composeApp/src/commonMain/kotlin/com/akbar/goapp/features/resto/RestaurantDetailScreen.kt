package com.akbar.goapp.features.resto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akbar.goapp.FoodRed
import com.akbar.goapp.Primary
import com.akbar.goapp.PrimaryContainer
import com.akbar.goapp.Screen
import com.akbar.goapp.SurfaceLine
import com.akbar.goapp.AppBottomBar
import com.akbar.goapp.Chip
import com.akbar.goapp.CircleIcon
import com.akbar.goapp.FloatingCartBar
import com.akbar.goapp.FoodImage
import com.akbar.goapp.SectionTitle
import com.akbar.goapp.SimpleTopBar

@Composable
fun RestaurantDetailScreen(open: (Screen) -> Unit) {
    Box(Modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(bottom = 146.dp)) {
            item { SimpleTopBar("Restaurant Details", Icons.Default.ArrowBack, Icons.Default.Star, topPadding = 0.dp) { open(Screen.Food) } }
            item { RestoHeroCard() }
            item { CategoryTabs() }
            item { MenuSection("Paket Hemat", true) }
            item { GridMenuSection() }
        }
        FloatingCartBar("2 items | Rp60.000", "Mas Budi, Kemang", "View Basket", Modifier.align(Alignment.BottomCenter).padding(bottom = 24.dp, start = 16.dp, end = 16.dp))
    }
}

@Composable
fun RestoHeroCard() {
    Box(Modifier.fillMaxWidth().height(250.dp)) {
        Box(Modifier.fillMaxWidth().height(195.dp).background(Brush.verticalGradient(listOf(Color(0xFF3A2416), Color(0xFF0F1714))))) {
            Text("Ayam\nBakar", color = Color.White.copy(alpha = 0.88f), fontSize = 42.sp, fontWeight = FontWeight.Black, modifier = Modifier.align(Alignment.Center))
        }
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White)
                .padding(18.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("Ayam Bakar Mas Budi", fontSize = 20.sp, fontWeight = FontWeight.Black)
                    Text("4.8  -  1.2km away  -  Open", color = Primary, fontSize = 16.sp)
                }
                Text("Promo", color = FoodRed, modifier = Modifier.clip(RoundedCornerShape(999.dp)).background(Color(0xFFFFE4E6)).padding(horizontal = 18.dp, vertical = 10.dp))
            }
        }
    }
}

@Composable
fun CategoryTabs() {
    Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Chip("Paket Hemat", true)
        Chip("Ayam & Bebek")
        Chip("Minuman")
    }
}

@Composable
fun MenuSection(title: String, listStyle: Boolean) {
    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionTitle(title, "See All")
        MenuRowCard("Paket Ayam Bakar", "Nasi + Ayam Bakar + Lalapan + Sambal", "Rp25.000")
        MenuRowCard("Bebek Goreng", "Bebek Goreng Gurih + Nasi Hangat", "Rp35.000")
    }
}

@Composable
fun MenuRowCard(title: String, desc: String, price: String) {
    Row(Modifier.clip(RoundedCornerShape(12.dp)).background(Color.White).padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        FoodImage(Modifier.size(100.dp), title)
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Black, fontSize = 16.sp)
            Text(desc, fontSize = 15.sp, color = Color(0xFF334155))
            Text(price, fontWeight = FontWeight.Black, fontSize = 18.sp, color = PrimaryContainer)
        }
        CircleIcon(Icons.Default.Add, PrimaryContainer, Color.White)
    }
}

@Composable
fun GridMenuSection() {
    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Ayam & Bebek", fontSize = 22.sp, fontWeight = FontWeight.Black)
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            FoodGridCard("Ayam Kalasan", "Rp28.000", Modifier.weight(1f))
            FoodGridCard("Bebek Bakar", "Rp38.000", Modifier.weight(1f))
        }
    }
}

@Composable
fun FoodGridCard(title: String, price: String, modifier: Modifier = Modifier) {
    Column(modifier.clip(RoundedCornerShape(12.dp)).background(Color.White)) {
        FoodImage(Modifier.fillMaxWidth().aspectRatio(1f), title)
        Column(Modifier.padding(10.dp)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(price, color = PrimaryContainer, fontWeight = FontWeight.Black, fontSize = 16.sp)
        }
    }
}
